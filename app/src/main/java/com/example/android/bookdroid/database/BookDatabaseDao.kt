package com.example.android.bookdroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface BookDatabaseDao {
    @Insert
    suspend fun insertBook(book: Book)

    @Query("SELECT identifier FROM book_id_table WHERE isbn = :isbn")
    suspend fun getBookIdentifierByIsbn(isbn: Long): String;

    @Query("SELECT * FROM book_table WHERE isbn = :isbn")
    suspend fun getBookByIsbn(isbn: Long): Book?;

    @Query("SELECT * FROM book_table")
    fun getBooks(): LiveData<List<Book>>;

    @Transaction
    @Query("SELECT * FROM shelve_table")
    fun getShelvesWithBooks(): LiveData<List<ShelveWithBooks>>

    @Transaction
    @Query("SELECT * FROM book_table")
    fun getBooksWithShelves(): LiveData<List<BookWithShelves>>
}