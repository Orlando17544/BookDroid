package com.example.android.bookdroid.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.databinding.BookItemBinding

class BookAdapter(val clickListener: BookListener): ListAdapter<Book,
        BookAdapter.BookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter
    .BookViewHolder {
        return BookViewHolder(
            BookItemBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class BookViewHolder(private var binding:
                                    BookItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book, clickListener: BookListener) {
            binding.book = book
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem;
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.title == newItem.title
        }
    }
}

class BookListener(val clickListener: (bookWithShelves: Book) -> Unit) {
    fun onClick(book: Book) = clickListener(book)
}