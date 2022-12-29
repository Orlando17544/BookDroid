package com.example.android.bookdroid.home

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.databinding.FragmentHomeBinding
import com.example.android.bookdroid.network.DownloadableBook

const val EXTRA_MESSAGE_DOWNLOADABLE_BOOK = "com.example.bookdroid.downloadable_book"
const val EXTRA_MESSAGE_DOWNLOADABLE_BOOKS = "com.example.bookdroid.downloadable_books"
const val EXTRA_MESSAGE_CATEGORY = "com.example.bookdroid.category"

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.viewModel = viewModel;

        binding.lifecycleOwner = viewLifecycleOwner;

        val recyclerViews = listOf(
            binding.educationDownloadableBooks,
            binding.fictionDownloadableBooks,
            binding.artDownloadableBooks,
            binding.religionDownloadableBooks
        )

        for (recyclerView in recyclerViews) {
            recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.adapter = DownloadableBookAdapter(DownloadableBookListener { downloadableBook ->
                viewModel.displayDownloadableBook(downloadableBook);
            })

        }

        viewModel.navigateToSelectedCategory.observe(viewLifecycleOwner, Observer { pair ->
            val intent = Intent(this.context, CategoryActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_DOWNLOADABLE_BOOKS, pair.first?.toTypedArray())
                putExtra(EXTRA_MESSAGE_CATEGORY, pair.second)
            }
            startActivity(intent)
        })

        viewModel.navigateToSelectedDownloadableBook.observe(viewLifecycleOwner, Observer { downloadableBook ->
            val intent = Intent(this.context, BookActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_DOWNLOADABLE_BOOK, downloadableBook)
            }
            startActivity(intent)
        })

        return binding.root;
    }
}