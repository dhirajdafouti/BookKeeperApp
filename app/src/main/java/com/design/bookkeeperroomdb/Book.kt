package com.design.bookkeeperroomdb

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
class Book(
    @NonNull
    @PrimaryKey val id: String,
    @ColumnInfo(name = "author_name")
    val author: String?,
    @ColumnInfo(name = "book_column")
    val book: String?
) {


}