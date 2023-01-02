package com.example.android.bookdroid.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.databinding.BookItemBinding

class BookAdapter(val clickListenerOpen: BookListener, val clickListenerOptions: BookListener): ListAdapter<BookWithShelves,
        BookAdapter.BookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter
    .BookViewHolder {
        return BookViewHolder(
            BookItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListenerOpen, clickListenerOptions)
    }

    class BookViewHolder(private var binding:
                                     BookItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookWithShelves: BookWithShelves, clickListenerOpen: BookListener, clickListenerOptions: BookListener) {
            binding.bookWithShelves = bookWithShelves
            binding.executePendingBindings()
            binding.clickListenerOpen = clickListenerOpen
            binding.clickListenerOptions = clickListenerOptions
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BookWithShelves>() {
        override fun areItemsTheSame(oldItem: BookWithShelves, newItem: BookWithShelves): Boolean {
            return oldItem === newItem;
        }

        override fun areContentsTheSame(oldItem: BookWithShelves, newItem: BookWithShelves): Boolean {
            return oldItem.book.title == newItem.book.title
                    && oldItem.shelves.size.equals(newItem.shelves.size);
        }
    }
}

class BookListener(val clickListener: (bookWithShelves: BookWithShelves) -> Unit) {
    fun onClick(bookWithShelves: BookWithShelves) = clickListener(bookWithShelves)
}