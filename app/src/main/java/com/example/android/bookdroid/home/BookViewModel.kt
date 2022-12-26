package com.example.android.bookdroid.home

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.BookApi
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

    init {
        getIdentifier();
    }

    fun getIdentifier() {
        viewModelScope.launch {
                downloadableBook.isbn?.let {
                    println("Isbn es: " + it);
                    identifier = database.getBookIdentifierByIsbn(it) };
        }
    }

    fun downloadBook() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val responseBody = BookApi.retrofitService.downloadFile("https://archive.org/download/" + identifier + "/" + identifier + ".pdf").body();
                val filePath = generateFilePath(downloadableBook)
                saveFile(responseBody, filePath)
            }
        }
    }

    fun generateFilePath(downloadableBook: DownloadableBook): String {
        val cw = ContextWrapper(getApplication<Application?>().applicationContext)
        // path to /data/data/yourapp/app_data/songs
        val directory = cw.getDir("books", Context.MODE_PRIVATE)
        return File(directory, downloadableBook.title + ".pdf").absolutePath
    }

    fun saveFile(body: ResponseBody?, filePath: String) {
        if (body==null)
            return
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
            return
        }catch (e:Exception){
            Log.e("saveFile",e.toString())
        }
        finally {
            input?.close()
        }
        return
    }
}