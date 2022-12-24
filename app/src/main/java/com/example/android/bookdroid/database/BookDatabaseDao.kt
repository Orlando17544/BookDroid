package com.example.android.bookdroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDatabaseDao {
    @Insert
    fun insertBookIdentifier(bookIdentifier: BookIdentifier)

    @Query("SELECT identifier FROM book_id_table WHERE isbn = :isbn")
    fun getBookIdentifierByIsbn(isbn: String): String;

    /*
    @Query("SELECT * FROM book_identifier_table WHERE category = :category")
    fun getBookIdentifiersByCategory(category: String): LiveData<Books>*/

    /*

    @Query("SELECT * FROM book_table")
    fun getArtBooks(): LiveData<Books>*/
}