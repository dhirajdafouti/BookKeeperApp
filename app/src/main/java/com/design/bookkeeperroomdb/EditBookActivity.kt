package com.design.bookkeeperroomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*
import java.nio.BufferUnderflowException

class EditBookActivity : AppCompatActivity() {

    var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val bundle: Bundle? = intent.extras
        bundle?.let {
            id = bundle.getString("id")
            val book = bundle.getString("book")
            val author = bundle.getString("author")
            etAuthorName.setText(author)
            etBookName.setText(book)
        }
        button_save.setOnClickListener { view ->
            val editIntent = Intent()
            val author = etAuthorName.text.toString()
            val book = etBookName.text.toString()
            editIntent.putExtra(UPDATED_AUTHOR, author)
            editIntent.putExtra(UPDATED_BOOK, book)
            editIntent.putExtra(ID, id)
            setResult(Activity.RESULT_OK, editIntent)
            Log.i(TAG, "The intent is send to main activity for the edit")
            finish()
        }
        button_cancel.setOnClickListener { view ->
            finish()
        }
    }

    companion object {
        const val ID: String = "book-id"
        const val UPDATED_AUTHOR: String = "author_name"
        const val UPDATED_BOOK: String = "book_name"
        private val TAG: String = EditBookActivity::class.java.simpleName
    }
}
