package com.example.android.bookdroid.library

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.ShelfWithBooks

class BooksViewModelFactory2(private val dataSource: BookDatabaseDao,
                             private val shelfWithBooks: ShelfWithBooks
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel2::class.java)) {
            return BooksViewModel2(dataSource, shelfWithBooks) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}