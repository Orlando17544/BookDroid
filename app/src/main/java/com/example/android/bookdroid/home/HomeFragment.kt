package com.example.android.bookdroid.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.databinding.FragmentHomeBinding
import com.example.android.bookdroid.network.DownloadableBook

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

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
            binding.artDownloadableBooks
        )

        for (recyclerView in recyclerViews) {
            recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.adapter = DownloadableBookAdapter(DownloadableBookListener { downloadableBook ->
                val intent = Intent(this.context, BookActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE, downloadableBook)
                }
                startActivity(intent)
            })

        }

        return binding.root;
    }
}