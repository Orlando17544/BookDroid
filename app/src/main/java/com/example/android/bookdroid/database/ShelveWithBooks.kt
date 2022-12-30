package com.example.android.bookdroid.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ShelveWithBooks (
    @Embedded val shelve: Shelve,
    @Relation(
        parentColumn = "shelveId",
        entityColumn = "bookId",
        associateBy = Junction(ShelveBookCrossRef::class)
    )
    val books: List<Book>
)