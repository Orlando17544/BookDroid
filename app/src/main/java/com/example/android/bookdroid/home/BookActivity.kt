package com.example.android.bookdroid.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.R
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.databinding.ActivityBookBinding
import com.example.android.bookdroid.databinding.ActivityCategoryBinding
import com.example.android.bookdroid.network.DownloadableBook

class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding
    private lateinit var viewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        val downloadableBook = intent.getParcelableExtra<DownloadableBook>(EXTRA_MESSAGE)

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;

        val viewModelFactory = downloadableBook?.let { BookViewModelFactory(it, dataSource) }

        // Get a reference to the ViewModel associated with this fragment.
        viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(BookViewModel::class.java)
        }!!
    }
}