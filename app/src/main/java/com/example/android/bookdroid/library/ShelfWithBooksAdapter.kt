package com.example.android.bookdroid.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bookdroid.database.ShelfWithBooks
import com.example.android.bookdroid.databinding.ShelfItemBinding
import com.example.android.bookdroid.databinding.ShelfWithBooksItemBinding

class ShelfWithBooksAdapter(val clickListenerOpen: ShelfWithBooksListener, val clickListenerOptions: ShelfWithBooksListener): ListAdapter<ShelfWithBooks,
        ShelfWithBooksAdapter.ShelfViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfWithBooksAdapter
    .ShelfViewHolder {
        return ShelfViewHolder(
            ShelfWithBooksItemBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ShelfWithBooksAdapter.ShelfViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListenerOpen, clickListenerOptions)
    }

    class ShelfViewHolder(private var binding:
                          ShelfWithBooksItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shelfWithBooks: ShelfWithBooks, clickListenerOpen: ShelfWithBooksListener, clickListenerOptions: ShelfWithBooksListener) {
            binding.shelfWithBooks = shelfWithBooks
            binding.executePendingBindings()
            binding.clickListenerOpen = clickListenerOpen
            binding.clickListenerOptions = clickListenerOptions
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ShelfWithBooks>() {
        override fun areItemsTheSame(oldItem: ShelfWithBooks, newItem: ShelfWithBooks): Boolean {
            return oldItem === newItem;
        }

        override fun areContentsTheSame(oldItem: ShelfWithBooks, newItem: ShelfWithBooks): Boolean {
            return oldItem.shelf.name == newItem.shelf.name
                    && oldItem.books.size.equals(newItem.books.size);
        }
    }
}

class ShelfWithBooksListener(val clickListener: (shelfWithBooks: ShelfWithBooks) -> Unit) {
    fun onClick(shelfWithBooks: ShelfWithBooks) = clickListener(shelfWithBooks)
}