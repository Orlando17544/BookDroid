package com.example.android.bookdroid.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.network.DownloadableBook
import kotlinx.coroutines.launch

class BooksViewModel(val database: BookDatabaseDao,
                     application: Application
) : AndroidViewModel(application) {
    val books: LiveData<List<Book>> = database.getBooks();
}