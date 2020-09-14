package com.design.bookkeeperroomdb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface BookDao {
    @Insert
    fun insert(book: Book)

    @Update
    fun updated(book: Book)

    @Delete
    fun deleted(book: Book)

    //Query through the Function.
    /*
    @Query(value = "SELECT *FROM books")
    fun getAllBooks(): LiveData<List<Book>>
*/

    //Query through the property.

    @get:Query(value = "SELECT *FROM books")
    val allBooks: LiveData<List<Book>>


}