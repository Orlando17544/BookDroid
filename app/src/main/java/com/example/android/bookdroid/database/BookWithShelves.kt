package com.example.android.bookdroid.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class BookWithShelves (
    @Embedded val book: Book,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "shelveId",
        associateBy = Junction(ShelveBookCrossRef::class)
    )
    val shelves: List<Shelve>
)