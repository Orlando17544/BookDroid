package com.example.android.bookdroid.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.ui.text.capitalize
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        val downloadableBooks = intent.getParcelableArrayExtra(EXTRA_MESSAGE_DOWNLOADABLE_BOOKS)
        val category = intent.getStringExtra(EXTRA_MESSAGE_CATEGORY).toString()

        supportActionBar?.title = category.capitalize();

        val viewModelFactory = CategoryViewModelFactory(downloadableBooks?.asList(), category);

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            viewModelFactory?.let {
                ViewModelProvider(
                    this, it
                ).get(CategoryViewModel::class.java)
            }!!

        binding.viewModel = viewModel;

        binding.lifecycleOwner = this;

        binding.downloadableBooks.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            this.adapter = DownloadableBookAdapter(DownloadableBookListener { downloadableBook ->
                viewModel.displayDownloadableBook(downloadableBook);
            })
        }

        viewModel.navigateToSelectedDownloadableBook.observe(this, Observer { downloadableBook ->
            val intent = Intent(this, BookActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_DOWNLOADABLE_BOOK, downloadableBook)
            }
            startActivity(intent)
        })

    }
}