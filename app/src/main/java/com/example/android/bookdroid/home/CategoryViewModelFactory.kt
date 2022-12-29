package com.example.android.bookdroid.home

import android.app.Application
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.network.DownloadableBook

class CategoryViewModelFactory(private val downloadableBooks: List<Parcelable>?, private val category: String): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(downloadableBooks, category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}