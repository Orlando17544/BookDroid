package com.example.android.bookdroid.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.R
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.databinding.ActivityBookBinding
import com.example.android.bookdroid.library.EXTRA_MESSAGE_BOOK
import com.example.android.bookdroid.network.DownloadableBook
import java.io.File

class DownloadableBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding
    private lateinit var viewModel: DownloadableBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_book)

        binding.lifecycleOwner = this;

        var downloadableBook = intent.getParcelableExtra<DownloadableBook?>(EXTRA_MESSAGE_DOWNLOADABLE_BOOK);
        var book = intent.getParcelableExtra<Book?>(EXTRA_MESSAGE_BOOK);

        if (downloadableBook == null) {
            binding.topAppBar.title = book?.title?.replaceFirstChar {
                it.uppercaseChar();
            }
        } else if (book == null) {
            binding.topAppBar.title = downloadableBook.title?.replaceFirstChar {
                it.uppercaseChar();
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            finish();
        }

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;

        val viewModelFactory = DownloadableBookViewModelFactory(downloadableBook, book?.isbn, dataSource, application);

        // Get a reference to the ViewModel associated with this fragment.
        viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(DownloadableBookViewModel::class.java)
        }!!

        binding.viewModel = viewModel;

        viewModel.book.observe(this, Observer { book ->
            val uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".fileprovider", File(book.path));

            // Open file with user selected app
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.setDataAndType(uri, "application/pdf")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
        })

        viewModel.downloadableBookLive.observe(this, Observer {
            viewModel.getBookInformation();
        })
    }
}