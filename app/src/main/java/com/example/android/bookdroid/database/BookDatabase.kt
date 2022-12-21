package com.example.android.bookdroid.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [BookIdentifier::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract val bookDatabaseDao: BookDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: BookDatabase? = null


        fun getInstance(context: Context): BookDatabase {
            /*val sRoomDatabaseCallback: Callback = object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    INSTANCE?.let {
                        //Insert
                        it.bookDatabaseDao.insertBookIdentifier()
                    }
                }
            }*/

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "book_database"
                    )
                        .fallbackToDestructiveMigration()
                        //.addCallback(sRoomDatabaseCallback)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}