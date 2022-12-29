package com.example.android.bookdroid.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.bookdroid.network.DownloadableBook
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "book_table")
data class Book (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "path")
    var path: String = "",

    @ColumnInfo(name = "isbn")
    var isbn: Long = 0,

    @ColumnInfo(name = "identifier")
    var identifier: String = "",

    @ColumnInfo(name = "cover")
    var cover: String? = "",

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "author")
    var author: String? = "",
) : Parcelable