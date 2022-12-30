package com.example.android.bookdroid.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shelve_table")
data class Shelve (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val shelveId: Long,

    @ColumnInfo(name = "name")
    val name: String
        )