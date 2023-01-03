package com.example.android.bookdroid.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish_table")
data class Wish (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wishId")
    var wishId: Long = 0,

    @ColumnInfo(name = "isbn")
    var isbn: Long = 0,

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "author")
    var author: String? = "",
)