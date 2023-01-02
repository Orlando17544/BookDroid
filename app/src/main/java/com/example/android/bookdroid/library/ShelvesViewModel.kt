package com.example.android.bookdroid.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.Shelf
import com.example.android.bookdroid.database.ShelfWithBooks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShelvesViewModel(val database: BookDatabaseDao) : ViewModel() {
    val shelvesWithBooks: LiveData<List<ShelfWithBooks>> = database.getShelvesWithBooks();

    fun addShelf(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newShelf = Shelf();

                newShelf.name = name;
                database.insertShelf(newShelf);
            }
        }
    }
}