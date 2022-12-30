package com.example.android.bookdroid.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.android.bookdroid.R
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.databinding.FragmentHomeBinding
import com.example.android.bookdroid.databinding.FragmentLibraryBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLibraryBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner;

        binding.viewPager2.adapter = BookShelfPagerAdapter(this.requireActivity());

        val tabLayoutMediator = TabLayoutMediator(
            binding.tabLayout, binding.viewPager2
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "Your books"
                1 -> tab.text = "Shelves"
            }
        }
        tabLayoutMediator.attach()

        return binding.root;
    }
}