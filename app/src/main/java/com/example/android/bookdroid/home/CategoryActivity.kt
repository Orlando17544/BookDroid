package com.example.android.bookdroid.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.R
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.databinding.ActivityCategoryBinding
import com.example.android.bookdroid.databinding.FragmentHomeBinding
import com.example.android.bookdroid.network.DownloadableBook

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category)

        val downloadableBook = intent.getParcelableExtra<DownloadableBook>(EXTRA_MESSAGE)

        val viewModelFactory = downloadableBook?.let { CategoryViewModelFactory(it) };

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            viewModelFactory?.let {
                ViewModelProvider(
                    this, it
                ).get(CategoryViewModel::class.java)
            }!!
    }
}