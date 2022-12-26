package com.example.android.bookdroid.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_id_table")
data class BookIdentifier(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "isbn")
    var isbn: Long = 0,

    @ColumnInfo(name = "identifier")
    var identifier: String = "",
)
