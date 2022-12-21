package com.example.android.bookdroid.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.databinding.FragmentHomeBinding
import com.example.android.bookdroid.network.DownloadableBook

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

        val horizontalLinearLayoutManager1 = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);

        binding.educationDownloadableBooks.layoutManager = horizontalLinearLayoutManager1;

        binding.educationDownloadableBooks.adapter = DownloadableBookAdapter();

        val horizontalLinearLayoutManager2 = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false);

        binding.fictionDownloadableBooks.layoutManager = horizontalLinearLayoutManager2;

        binding.fictionDownloadableBooks.adapter = DownloadableBookAdapter();

        return binding.root;
    }
}