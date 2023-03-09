package com.example.android.bookdroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookIdentifier::class, Book::class, Shelf::class, ShelfBookCrossRef::class, Wish::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract val bookDatabaseDao: BookDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "book_database.db.db"
                    )
                        //.fallbackToDestructiveMigration()
                        .createFromAsset("database/book_database.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}