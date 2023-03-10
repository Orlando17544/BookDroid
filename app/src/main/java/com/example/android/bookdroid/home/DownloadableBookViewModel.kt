package com.example.android.bookdroid.home

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.lifecycle.*
import com.example.android.bookdroid.BookApi
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.Wish
import com.example.android.bookdroid.network.DownloadableBook
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.reflect.Type


class DownloadableBookViewModel(
    var downloadableBook: DownloadableBook?,
    val isbn: String?,
    val database: BookDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    lateinit var identifier: String;

    private var _downloadState = MutableLiveData<String>();

    val downloadState: LiveData<String>
        get() = _downloadState

    private var _wishState = MutableLiveData<String>();

    val wishState: LiveData<String>
        get() = _wishState

    private var _downloadableBookLive = MutableLiveData<DownloadableBook>();

    val downloadableBookLive: LiveData<DownloadableBook>
        get() = _downloadableBookLive

    private var _book = MutableLiveData<Book>();

    val book: LiveData<Book>
        get() = _book

    init {
        viewModelScope.launch {
            if (downloadableBook == null) {
                getBookFromApi();
            } else {
                _downloadableBookLive.value = downloadableBook!!;
                getBookInformation();
            }
        }
    }

    private fun getBookFromApi() {
            isbn?.let {
                BookApi.retrofitService.getBook("ISBN:" + isbn).enqueue(
                    object: Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {

                            var jsonResponse: String = response.body().toString();

                            val type: Type = Types.newParameterizedType(Map::class.java, String::class.java, DownloadableBook::class.java);
                            val adapter = BookApi.moshiService()?.adapter<Map<String, DownloadableBook>>(type);
                            val booksMap: Map<String, DownloadableBook>? = adapter?.fromJson(jsonResponse);

                            val keys: MutableList<String>? = booksMap?.keys?.toMutableList()
                            val values: List<DownloadableBook>? = booksMap?.values?.toMutableList()

                            for (i in keys?.indices!!) {
                                keys[i] = keys[i].replace("ISBN:", "");
                                values?.get(i)?.isbn = keys[i];
                            }

                            _downloadableBookLive.value = values?.get(0);
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            println("Failure" + t.message);
                        }
                    }
                )
            }
    }

    fun getBookInformation() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getIdentifier();
                checkBook();
                checkWish();
            }
        }
    }

    suspend fun getIdentifier() {
        _downloadableBookLive.value?.isbn?.let {
            identifier = database.getBookIdentifierByIsbn(it) };
    }

    suspend fun checkBook() {
        _downloadableBookLive.value?.isbn?.let {
            val book = database.getBookByIsbn(it);

            if (book == null) {
                _downloadState.postValue("not_downloaded");
            } else {
                _downloadState.postValue("downloaded");
            }
        }
    }

    suspend fun checkWish() {
        _downloadableBookLive.value?.isbn?.let {
            val wish = database.getWishByIsbn(it);

            if (wish == null) {
                _wishState.postValue("deleted");
            } else {
                _wishState.postValue("added");
            }
        }
    }

    fun downloadBook() {
        _downloadState.value = "downloading"
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val responseBody = BookApi.retrofitService.downloadFile("https://archive.org/download/" + identifier + "/" + identifier + ".pdf").body();
                if (responseBody != null) {
                    val filePath = _downloadableBookLive?.value?.let { generateFilePath(it) }
                    filePath?.let { saveFile(responseBody, it) }
                    filePath?.let { insertBook(it) }

                    if (hasWish()) {
                        removeFromWishList();
                    }

                    _downloadState.postValue("downloaded");
                } else {
                    _downloadState.postValue("unavailable")
                }
            }
        }
    }

    suspend fun hasWish(): Boolean {
        val isbn = _downloadableBookLive.value?.isbn;

        val wish = database.getWishByIsbn(isbn!!);

        if (wish != null) {
            return true;
        } else {
            return false;
        }
    }

    fun generateFilePath(downloadableBook: DownloadableBook): String {
        _downloadableBookLive.value?.title?.let {
            val dir: File = File(getApplication<Application>().getFilesDir().absolutePath, it)
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
        newBook.isbn = _downloadableBookLive.value?.isbn!!;
        newBook.identifier = identifier;
        newBook.cover = _downloadableBookLive.value?.cover?.get("medium");
        newBook.title = _downloadableBookLive.value?.title;
        newBook.author = _downloadableBookLive.value?.authors?.get(0)?.get("name");
        database.insertBook(newBook);
    }

    fun openBook() {
        viewModelScope.launch {
            _downloadableBookLive.value?.isbn?.let {
                _book.value = database.getBookByIsbn(it)
            }
        }
    }

    fun addToWishList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newWish = Wish();

                newWish.isbn = _downloadableBookLive.value?.isbn!!;
                newWish.title = _downloadableBookLive.value?.title;
                newWish.author = _downloadableBookLive.value!!.authors?.get(0)?.get("name");

                database.insertWish(newWish);
                _wishState.postValue("added");
            }
        }
    }

    fun removeFromWishList() {
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                val isbn = _downloadableBookLive.value?.isbn;

                val wish = database.getWishByIsbn(isbn!!);

                database.deleteWish(wish);
                _wishState.postValue("deleted");
            }
        }
    }
}