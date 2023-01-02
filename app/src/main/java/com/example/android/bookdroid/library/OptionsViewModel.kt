package com.example.android.bookdroid.library

import android.app.Application
import androidx.lifecycle.*
import com.example.android.bookdroid.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OptionsViewModel(val bookWithShelves: BookWithShelves, val database: BookDatabaseDao) : ViewModel() {

    private var _close = MutableLiveData<String?>();

    val close: LiveData<String?>
        get() = _close

    private var _navigateToShelvesActivity = MutableLiveData<Pair<BookWithShelves, String>>();

    val navigateToShelvesActivity: LiveData<Pair<BookWithShelves, String>>
        get() = _navigateToShelvesActivity

    fun deleteBook() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.deleteBook(bookWithShelves.book);
                _close.postValue(null);
            }
        }
    }

    fun addToShelf(shelf: Shelf) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newShelfBook = ShelfBookCrossRef();

                newShelfBook.bookId = bookWithShelves.book.bookId;
                newShelfBook.shelfId = shelf.shelveId;

                database.insertShelfBook(newShelfBook);
            }
        }
    }

    fun removeFromShelf() {

    }

    fun navigateToShelvesActivity(action: String) {
        _navigateToShelvesActivity.value = Pair(bookWithShelves, action);
    }
}