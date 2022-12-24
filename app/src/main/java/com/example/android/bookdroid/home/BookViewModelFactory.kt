package com.example.android.bookdroid.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.network.DownloadableBook

class BookViewModelFactory(
    private val downloadableBook: DownloadableBook,
    private val dataSource: BookDatabaseDao
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(downloadableBook, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}