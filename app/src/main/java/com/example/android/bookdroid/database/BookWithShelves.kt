package com.example.android.bookdroid.database

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookWithShelves (
    @Embedded val book: Book,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "shelfId",
        associateBy = Junction(ShelfBookCrossRef::class)
    )
    val shelves: List<Shelf>
) : Parcelable