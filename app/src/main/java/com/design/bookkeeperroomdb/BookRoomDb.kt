package com.design.bookkeeperroomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Book::class], version = 1)
abstract class BookRoomDb : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var bookRoomInstance: BookRoomDb? = null

        fun getDatabase(context: Context): BookRoomDb? {
            if (bookRoomInstance == null) {
                synchronized(BookRoomDb::class.java) {
                    if (bookRoomInstance == null) {
                        bookRoomInstance = Room.databaseBuilder<BookRoomDb>(
                            context.applicationContext,
                            BookRoomDb::class.java, "book_database"
                        ).build()
                    }
                }
            }

            return bookRoomInstance
        }


    }
}