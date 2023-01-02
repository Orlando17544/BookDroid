package com.example.android.bookdroid.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ShelfWithBooks (
    @Embedded val shelf: Shelf,
    @Relation(
        parentColumn = "shelfId",
        entityColumn = "bookId",
        associateBy = Junction(ShelfBookCrossRef::class)
    )
    val books: List<Book>
)