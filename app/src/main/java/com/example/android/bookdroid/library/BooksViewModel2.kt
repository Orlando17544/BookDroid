package com.example.android.bookdroid.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.database.ShelfWithBooks

class BooksViewModel2(val database: BookDatabaseDao,
                      val shelfWithBooks: ShelfWithBooks
) : ViewModel() {
    val books = shelfWithBooks.books;
}