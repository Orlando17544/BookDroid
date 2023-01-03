package com.example.android.bookdroid.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDatabaseDao {
    @Insert
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT identifier FROM book_id_table WHERE isbn = :isbn")
    suspend fun getBookIdentifierByIsbn(isbn: Long): String;

    @Query("SELECT * FROM book_table WHERE isbn = :isbn")
    suspend fun getBookByIsbn(isbn: Long): Book?;

    @Transaction
    @Query("SELECT * FROM shelf_table")
    fun getShelvesWithBooks(): LiveData<List<ShelfWithBooks>>

    @Transaction
    @Query("SELECT * FROM book_table")
    fun getBooksWithShelves(): LiveData<List<BookWithShelves>>

    @Insert
    suspend fun insertShelf(shelf: Shelf)

    @Delete
    suspend fun deleteShelf(shelf: Shelf)

    @Query("SELECT * FROM shelf_table WHERE shelfId NOT IN " +
            "(SELECT shelfId FROM shelfbookcrossref WHERE bookId = :bookId)")
    suspend fun getAvailableShelvesByBookId(bookId: Long): List<Shelf>;

    @Insert
    suspend fun insertShelfBook(shelfBookCrossRef: ShelfBookCrossRef)

    @Delete
    suspend fun deleteShelfBook(shelfBookCrossRef: ShelfBookCrossRef)

    @Query("SELECT * FROM shelfbookcrossref WHERE shelfId = :shelfId")
    fun getShelfBookCrossRefsByShelfId(shelfId: Long): List<ShelfBookCrossRef>;

    @Query("SELECT * FROM shelfbookcrossref WHERE bookId = :bookId")
    fun getShelfBookCrossRefsByBookId(bookId: Long): List<ShelfBookCrossRef>;

    @Query("SELECT * FROM wish_table")
    fun getWishList(): LiveData<List<Wish>>;

    @Delete
    suspend fun deleteWish(wish: Wish);
}