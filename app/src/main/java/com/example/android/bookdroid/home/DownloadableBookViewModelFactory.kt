package com.example.android.bookdroid.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.network.DownloadableBook

class DownloadableBookViewModelFactory(
    private val downloadableBook: DownloadableBook?,
    private val isbn: Long?,
    private val dataSource: BookDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DownloadableBookViewModel::class.java)) {
            return DownloadableBookViewModel(downloadableBook, isbn, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}