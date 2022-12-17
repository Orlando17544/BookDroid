package com.example.android.bookdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener { item ->
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

        bottomNavigationView.selectedItemId = R.id.page_1;
    }
}