package com.design.bookkeeperroomdb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() ,BookListAdapter.onDeleteClickListener{
    override fun onDeleteClickListener(mybook: Book) {
       bookViewModel.delete(mybook)
        Toast.makeText(applicationContext, "Successfully Deleted!!!", Toast.LENGTH_LONG).show()
    }

    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bookListAdapter = BookListAdapter(this,this)
        recycler_view.adapter = bookListAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val intent = Intent(this, Main2Activity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVTIY_REQUEST_CODE)
        }
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        bookViewModel.allBooks.observe(this, androidx.lifecycle.Observer { books ->
            books?.let {
                bookListAdapter.setBookListData(books)
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_NOTE_ACTIVTIY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val author = data!!.getStringExtra(Main2Activity.NEW_AUTHOR)
            val book = data.getStringExtra(Main2Activity.NEW_BOOK)
            val id = UUID.randomUUID().toString()
            val bookDetails = Book(id, author, book)
            bookViewModel.insert(bookDetails)
            Toast.makeText(applicationContext, "Successfully Inserted!!!", Toast.LENGTH_LONG).show()
        } else if (requestCode == UPDATED_NOTE_ACTIVTIY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val author = data!!.getStringExtra(EditBookActivity.UPDATED_AUTHOR)
            val book = data.getStringExtra(EditBookActivity.UPDATED_BOOK)
            val id = data.getStringExtra(EditBookActivity.ID)
            val bookDetails = Book(id, author, book)
            bookViewModel.updated(bookDetails)
            Toast.makeText(applicationContext, "Successfully Updated!!!", Toast.LENGTH_LONG).show()
        } else {
            Log.i(TAG, "Data is not saved!!!!")

        }
    }

    companion object {
        private const val NEW_NOTE_ACTIVTIY_REQUEST_CODE: Int = 1
        const val UPDATED_NOTE_ACTIVTIY_REQUEST_CODE: Int = 2
        private val TAG: String = MainActivity::class.java.simpleName
    }


}
