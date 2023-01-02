package com.example.android.bookdroid.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "shelf_table")
data class Shelf (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shelfId")
    var shelveId: Long = 0,

    @ColumnInfo(name = "name")
    var name: String = ""
) : Parcelable