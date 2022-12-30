package com.example.android.bookdroid.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.android.bookdroid.database.BookDatabaseDao

class LibraryViewModel(val database: BookDatabaseDao, application: Application) : AndroidViewModel(application) {

}