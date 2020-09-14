package com.design.bookkeeperroomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setCLickListener()
    }

    fun setCLickListener(): Unit {
        button_save.setOnClickListener { view ->
            val resultIntent = Intent()
            if (TextUtils.isEmpty(etAuthorName.text) || TextUtils.isEmpty(etBookName.text)) {
                setResult(Activity.RESULT_CANCELED, resultIntent)
            } else {
                val author = etAuthorName.text.toString()
                val book = etBookName.text.toString()
                resultIntent.putExtra(NEW_AUTHOR, author)
                resultIntent.putExtra(NEW_BOOK, book)
                setResult(Activity.RESULT_OK, resultIntent)
                Log.i(TAG, "The intent is send to main activity")
            }
            finish()
        }

        button_cancel.setOnClickListener { view ->
            finish()
        }
    }

    companion object {
        const val NEW_AUTHOR: String = "author"
        const val NEW_BOOK: String = "book"
        private val TAG: String = Main2Activity::class.java.simpleName

    }
}
