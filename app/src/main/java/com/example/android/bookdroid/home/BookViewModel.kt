package com.example.android.bookdroid.home

import androidx.lifecycle.ViewModel
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.network.DownloadableBook

class BookViewModel(val downloadableBook: DownloadableBook,
                    val database: BookDatabaseDao,) : ViewModel() {


}