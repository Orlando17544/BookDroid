package com.example.android.bookdroid.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.database.Shelf
import com.example.android.bookdroid.home.DownloadableBookApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddRemoveShelvesViewModel(val database: BookDatabaseDao, var bookWithShelves: BookWithShelves?) : ViewModel() {

    private val _shelves = MutableLiveData<List<Shelf>>()

    val shelves: LiveData<List<Shelf>>
        get() = _shelves

    init {
        if (bookWithShelvesEmpty()) {
            getAllShelves();
        } else {
            getShelvesFromBook();
        }
    }

    fun bookWithShelvesEmpty(): Boolean {
        if (bookWithShelves == null) {
            return true;
        } else {
            return false;
        }
    }

    fun getAllShelves() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _shelves.postValue(database.getShelves());
            }
        }
    }

    fun getShelvesFromBook() {
        _shelves.value = bookWithShelves?.shelves;
    }
}