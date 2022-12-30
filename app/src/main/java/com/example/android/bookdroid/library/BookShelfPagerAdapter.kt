package com.example.android.bookdroid.library

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BookShelfPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val fragment = BooksFragment();
                return fragment;
            }
            1 -> {
                val fragment = ShelvesFragment();
                return fragment;
            }
            else -> {
                val fragment = Fragment();
                return fragment
            }
        }
    }
}