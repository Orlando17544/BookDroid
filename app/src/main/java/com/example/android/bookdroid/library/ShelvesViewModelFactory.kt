package com.example.android.bookdroid.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao

class ShelvesViewModelFactory(private val dataSource: BookDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShelvesViewModel::class.java)) {
            return ShelvesViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}