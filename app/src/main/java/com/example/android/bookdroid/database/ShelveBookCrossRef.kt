package com.example.android.bookdroid.database

import androidx.room.Entity

@Entity(primaryKeys = ["shelveId", "bookId"])
data class ShelveBookCrossRef (
    val shelveId: Long,
    val bookId: Long
)
