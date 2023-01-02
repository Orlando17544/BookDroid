package com.example.android.bookdroid.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.BookWithShelves

class AddRemoveShelvesViewModelFactory(private val dataSource: BookDatabaseDao, private var bookWithShelves: BookWithShelves?): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRemoveShelvesViewModel::class.java)) {
            return AddRemoveShelvesViewModel(dataSource, bookWithShelves) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}