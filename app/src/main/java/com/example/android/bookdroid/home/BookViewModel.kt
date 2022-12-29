package com.example.android.bookdroid.home

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.lifecycle.*
import com.example.android.bookdroid.BookApi
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.network.DownloadableBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class BookViewModel(val downloadableBook: DownloadableBook,
                    val database: BookDatabaseDao,
                    application: Application) : AndroidViewModel(application) {
    lateinit var identifier: String;

    private var _downloadState = MutableLiveData<String>();

    val downloadState: LiveData<String>
        get() = _downloadState

    private var _book = MutableLiveData<Book>();

    val book: LiveData<Book>
        get() = _book

    init {
        viewModelScope.launch {
            getIdentifier();
            checkBook();
        }
    }

    suspend fun getIdentifier() {
        downloadableBook.isbn?.let {
            identifier = database.getBookIdentifierByIsbn(it) };
    }

    suspend fun checkBook() {
        downloadableBook.isbn?.let {
            val book = database.getBookByIsbn(it);

            if (book == null) {
                _downloadState.value = "not_downloaded";
            } else {
                _downloadState.value = "downloaded";
            }
        };
    }

    fun downloadBook() {
        _downloadState.value = "downloading"
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val responseBody = BookApi.retrofitService.downloadFile("https://archive.org/download/" + identifier + "/" + identifier + ".pdf").body();
                if (responseBody != null) {
                    val filePath = generateFilePath(downloadableBook)
                    saveFile(responseBody, filePath)
                    insertBook(filePath)
                    _downloadState.postValue("downloaded");
                } else {
                    _downloadState.postValue("unavailable")
                }
            }
        }
    }

    fun generateFilePath(downloadableBook: DownloadableBook): String {
        downloadableBook.title?.let {
            val dir: File = File(getApplication<Application>().getFilesDir().absolutePath, downloadableBook.title)
            return dir.absolutePath;
        }
        return "";
    }

    suspend fun saveFile(body: ResponseBody?, filePath: String) {
        withContext(Dispatchers.IO) {
            body?.let {
                var input: InputStream? = null
                try {
                    input = body.byteStream()
                    //val file = File(getCacheDir(), "cacheFileAppeal.srl")
                    val fos = FileOutputStream(filePath)
                    fos.use { output ->
                        val buffer = ByteArray(4 * 1024) // or other buffer size
                        var read: Int
                        while (input.read(buffer).also { read = it } != -1) {
                            output.write(buffer, 0, read)
                        }
                        output.flush()
                    }
                } catch (e:Exception) {
                    Log.e("saveFile",e.toString())
                }
                finally {
                    input?.close()
                }
            }
        }
    }

    suspend fun insertBook(filePath: String) {
        val newBook = Book();

        newBook.path = filePath;
        newBook.isbn = downloadableBook.isbn!!;
        newBook.identifier = identifier;
        newBook.cover = downloadableBook.cover?.get("medium");
        newBook.title = downloadableBook.title;
        newBook.author = downloadableBook.authors?.get(0)?.get("name");
        database.insertBook(newBook);
    }

    fun openBook() {
        viewModelScope.launch {
            downloadableBook.isbn?.let {
                _book.value = database.getBookByIsbn(it)
            }
        }
    }
}