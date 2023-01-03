package com.example.android.bookdroid.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.Wish
import com.example.android.bookdroid.databinding.BookItemBinding
import com.example.android.bookdroid.databinding.WishListItemBinding

class WishListAdapter(val clickListenerOpen: WishListener, val clickListenerOptions: WishListener): ListAdapter<Wish,
        WishListAdapter.WishViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapter
    .WishViewHolder {
        return WishViewHolder(
            WishListItemBinding.inflate(
                LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: WishListAdapter.WishViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListenerOpen, clickListenerOptions)
    }

    class WishViewHolder(private var binding:
                         WishListItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wish: Wish, clickListenerOpen: WishListener, clickListenerOptions: WishListener) {
            binding.wish = wish
            binding.executePendingBindings()
            binding.clickListenerOpen = clickListenerOpen
            binding.clickListenerOptions = clickListenerOptions
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Wish>() {
        override fun areItemsTheSame(oldItem: Wish, newItem: Wish): Boolean {
            return oldItem === newItem;
        }

        override fun areContentsTheSame(oldItem: Wish, newItem: Wish): Boolean {
            return oldItem.title == newItem.title
        }
    }
}

class WishListener(val clickListener: (wish: Wish, options: View) -> Unit) {
    fun onClick(wish: Wish, options: View) = clickListener(wish, options)
}