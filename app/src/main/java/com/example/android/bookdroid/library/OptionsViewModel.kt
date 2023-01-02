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

                val shelfBookCrossRefs = database.getShelfBookCrossRefsByBookId(bookWithShelves.book.bookId);

                for (shelfBookCrossRef in shelfBookCrossRefs) {
                    database.deleteShelfBook(shelfBookCrossRef);
                }
                _close.postValue(null);
            }
        }
    }

    fun navigateToShelvesActivity(action: String) {
        _navigateToShelvesActivity.value = Pair(bookWithShelves, action);
    }
}