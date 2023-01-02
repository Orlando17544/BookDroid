package com.example.android.bookdroid.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.database.*
import com.example.android.bookdroid.home.DownloadableBookApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddRemoveShelvesViewModel(val database: BookDatabaseDao, var bookWithShelves: BookWithShelves,
    val action: String) : ViewModel() {

    private val _shelves = MutableLiveData<List<Shelf>>()

    val shelves: LiveData<List<Shelf>>
        get() = _shelves

    init {
        if (addBookToShelf()) {
            getAvailableShelves();
        } else {
            getShelvesFromBook();
        }
    }

    fun addBookToShelf(): Boolean {
        if (action.equals("add")) {
            return true;
        } else {
            return false;
        }
    }

    fun getAvailableShelves() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _shelves.postValue(database.getAvailableShelvesByBookId(bookWithShelves.book.bookId));
            }
        }
    }

    fun getShelvesFromBook() {
        _shelves.value = bookWithShelves?.shelves;
    }

    fun addBookToShelf(shelf: Shelf) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newShelfBook = ShelfBookCrossRef();

                newShelfBook.bookId = bookWithShelves.book.bookId;
                newShelfBook.shelfId = shelf.shelveId;

                database.insertShelfBook(newShelfBook);
            }
        }
    }

    fun removeBookFromShelf(shelf: Shelf) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val shelfBook = ShelfBookCrossRef();

                shelfBook.bookId = bookWithShelves.book.bookId;
                shelfBook.shelfId = shelf.shelveId;

                database.deleteShelfBook(shelfBook);
            }
        }
    }

    fun executeActionInBook(shelf: Shelf) {
        if (action.equals("add")) {
            addBookToShelf(shelf)
        } else {
            removeBookFromShelf(shelf)
        }
    }
}