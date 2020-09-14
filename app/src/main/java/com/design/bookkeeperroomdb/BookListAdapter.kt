package com.design.bookkeeperroomdb

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class BookListAdapter(private val context: Context,private val ondeleteClickListener: onDeleteClickListener) :
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    interface onDeleteClickListener {
        fun onDeleteClickListener(mybook: Book)
    }

    private var bookList: List<Book> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemview = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return BookViewHolder(itemview)
    }


    override fun getItemCount(): Int = bookList.size


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.setData(book.author, book.book, position)
        holder.setListener()

    }

    fun setBookListData(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pos: Int = 0
        fun setData(author: String?, book: String?, position: Int) {
            itemView.tvAuthor.text = author
            itemView.tvBook.text = book
            this.pos = position
        }

        fun setListener() {
            itemView.ivRowEdit.setOnClickListener { view ->
                val intent = Intent(context, EditBookActivity::class.java)
                intent.putExtra(ID, bookList[pos].id)
                intent.putExtra("author", bookList[pos].author)
                intent.putExtra("book", bookList[pos].book)
                (context as Activity).startActivityForResult(
                    intent,
                    MainActivity.UPDATED_NOTE_ACTIVTIY_REQUEST_CODE
                )
            }

            itemView.ivRowDelete.setOnClickListener{
                ondeleteClickListener.onDeleteClickListener((bookList[pos]))
            }

        }

    }

    companion object {
        private val ID: String = "id"
    }
}