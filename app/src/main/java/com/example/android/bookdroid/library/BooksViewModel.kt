package com.example.android.bookdroid.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.network.DownloadableBook
import kotlinx.coroutines.launch

class BooksViewModel(val database: BookDatabaseDao,
                     application: Application
) : AndroidViewModel(application) {
    val booksWithShelves: LiveData<List<BookWithShelves>> = database.getBooksWithShelves();
}