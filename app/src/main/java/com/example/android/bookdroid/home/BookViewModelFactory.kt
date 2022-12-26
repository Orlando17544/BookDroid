package com.example.android.bookdroid.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.network.DownloadableBook

class BookViewModelFactory(
    private val downloadableBook: DownloadableBook,
    private val dataSource: BookDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(downloadableBook, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}