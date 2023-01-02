package com.example.android.bookdroid.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.Shelf
import com.example.android.bookdroid.databinding.ShelfItemBinding

class ShelfAdapter(val clickListener: ShelfListener): ListAdapter<Shelf,
        ShelfAdapter.ShelfViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfAdapter
    .ShelfViewHolder {
        return ShelfViewHolder(
            ShelfItemBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ShelfAdapter.ShelfViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ShelfViewHolder(private var binding:
                          ShelfItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shelf: Shelf, clickListener: ShelfListener) {
            binding.shelf = shelf
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Shelf>() {
        override fun areItemsTheSame(oldItem: Shelf, newItem: Shelf): Boolean {
            return oldItem === newItem;
        }

        override fun areContentsTheSame(oldItem: Shelf, newItem: Shelf): Boolean {
            return oldItem.name == newItem.name
        }
    }
}

class ShelfListener(val clickListener: (shelf: Shelf) -> Unit) {
    fun onClick(shelf: Shelf) = clickListener(shelf)
}