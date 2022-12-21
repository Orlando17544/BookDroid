package com.example.android.bookdroid.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bookdroid.databinding.BookItemBinding
import com.example.android.bookdroid.network.DownloadableBook

class DownloadableBookAdapter: ListAdapter<DownloadableBook,
        DownloadableBookAdapter.DownloadableBookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadableBookAdapter
    .DownloadableBookViewHolder {
        return DownloadableBookViewHolder(BookItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DownloadableBookAdapter.DownloadableBookViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DownloadableBookViewHolder(private var binding:
                                     BookItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(downloadableBook: DownloadableBook) {
            binding.downloadableBook = downloadableBook
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DownloadableBook>() {
        override fun areItemsTheSame(oldItem: DownloadableBook, newItem: DownloadableBook): Boolean {
            return oldItem === newItem;
        }

        override fun areContentsTheSame(oldItem: DownloadableBook, newItem: DownloadableBook): Boolean {
            return oldItem.title == newItem.title;
        }
    }
}