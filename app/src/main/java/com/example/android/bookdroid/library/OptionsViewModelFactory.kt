package com.example.android.bookdroid.library

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.home.CategoryViewModel

class OptionsViewModelFactory(private val bookWithShelves: BookWithShelves, private val dataSource: BookDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OptionsViewModel::class.java)) {
            return OptionsViewModel(bookWithShelves, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}