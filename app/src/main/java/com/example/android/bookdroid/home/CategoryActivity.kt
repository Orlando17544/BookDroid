package com.example.android.bookdroid.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.R
import com.example.android.bookdroid.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_category)

        val downloadableBooks = intent.getParcelableArrayExtra(EXTRA_MESSAGE_DOWNLOADABLE_BOOKS)
        val category = intent.getStringExtra(EXTRA_MESSAGE_CATEGORY).toString()

        binding.topAppBar.title = category.replaceFirstChar {
            it.uppercaseChar();
        }

        binding.topAppBar.setNavigationOnClickListener {
            finish();
        }

        val viewModelFactory = CategoryViewModelFactory(downloadableBooks?.asList(), category);

        // Get a reference to the ViewModel associated with this fragment.
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CategoryViewModel::class.java)

        binding.viewModel = viewModel;

        binding.lifecycleOwner = this;

        binding.downloadableBooks.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            adapter = DownloadableBookAdapter(DownloadableBookListener { downloadableBook ->
                viewModel.displayDownloadableBook(downloadableBook);
            })
        }

        viewModel.navigateToSelectedDownloadableBook.observe(this, Observer { downloadableBook ->
            val intent = Intent(this, DownloadableBookActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_DOWNLOADABLE_BOOK, downloadableBook)
            }
            startActivity(intent)
        })

    }
}