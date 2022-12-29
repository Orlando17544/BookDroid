package com.example.android.bookdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.home.HomeFragment
import com.example.android.bookdroid.library.LibraryFragment
import com.example.android.bookdroid.wishlist.WishListFragment
import com.example.android.bookdroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            bottomNavigation.setOnItemSelectedListener { item ->
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                lateinit var newFragment: Fragment;

                when(item.itemId) {
                    R.id.page_1 -> {
                        // Respond to navigation item 1 click
                        newFragment = HomeFragment();
                    }
                    R.id.page_2 -> {
                        // Respond to navigation item 2 click
                        newFragment = LibraryFragment();
                    }
                    R.id.page_3 -> {
                        newFragment = WishListFragment();
                    }
                    else -> false
                }
                fragmentTransaction.replace(R.id.fragment_container, newFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
                return@setOnItemSelectedListener true;
            }

            bottomNavigation.selectedItemId = R.id.page_1;
        }
    }


}