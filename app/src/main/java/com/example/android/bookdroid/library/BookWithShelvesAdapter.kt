package com.example.android.bookdroid.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.databinding.BookWithShelvesItemBinding

class BookWithShelvesAdapter(val clickListenerOpen: BookWithShelvesListener, val clickListenerOptions: BookWithShelvesListener): ListAdapter<BookWithShelves,
        BookWithShelvesAdapter.BookWithShelvesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookWithShelvesAdapter
    .BookWithShelvesViewHolder {
        return BookWithShelvesViewHolder(
            BookWithShelvesItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BookWithShelvesAdapter.BookWithShelvesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListenerOpen, clickListenerOptions)
    }

    class BookWithShelvesViewHolder(private var binding:
                                     BookWithShelvesItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookWithShelves: BookWithShelves, clickListenerOpen: BookWithShelvesListener, clickListenerOptions: BookWithShelvesListener) {
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

class BookWithShelvesListener(val clickListener: (bookWithShelves: BookWithShelves) -> Unit) {
    fun onClick(bookWithShelves: BookWithShelves) = clickListener(bookWithShelves)
}