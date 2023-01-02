package com.example.android.bookdroid.database

import androidx.room.Entity

@Entity(primaryKeys = ["shelfId", "bookId"])
data class ShelfBookCrossRef (
    var shelfId: Long = 0,
    var bookId: Long = 0
)
