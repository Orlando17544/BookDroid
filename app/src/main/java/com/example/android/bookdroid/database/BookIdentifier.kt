package com.example.android.bookdroid.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_identifier_table")
data class BookIdentifier(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "identifier")
    val identifier: String,

    @ColumnInfo(name = "category")
    val category: String
)
