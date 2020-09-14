package com.design.bookkeeperroomdb

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookDao: BookDao
    val allBooks: LiveData<List<Book>>

    init {
        val bookDb = BookRoomDb.getDatabase(application)
        bookDao = bookDb!!.bookDao()
        allBooks = bookDao.allBooks
    }

    fun insert(book: Book) {
        InsertBookDetailsToDb(bookDao).execute(book)
    }

    fun delete(book: Book) {
        DeleteBookDetailsToDb(bookDao).execute(book)
    }

    fun updated(book: Book) {
        UpdatedBookDetails(bookDao).execute(book)
    }


    companion object {
        private class InsertBookDetailsToDb(private val bookDao: BookDao) :
            AsyncTask<Book, Void, Void>() {
            override fun doInBackground(vararg books: Book): Void? {
                bookDao.insert(books[0])
                return null
            }
        }
    }

    private class UpdatedBookDetails(private val bookDao: BookDao) : AsyncTask<Book, Void, Void>() {
        override fun doInBackground(vararg updatedBooks: Book): Void? {
            bookDao.updated(updatedBooks[0])
            return null
        }

    }

    private class DeleteBookDetailsToDb(private val bookDao: BookDao) : AsyncTask<Book, Void, Void>() {
        override fun doInBackground(vararg updatedBooks: Book): Void? {
            bookDao.deleted(updatedBooks[0])
            return null
        }

    }
}


