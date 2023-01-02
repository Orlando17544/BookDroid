package com.example.android.bookdroid.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.R
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.database.ShelfWithBooks
import com.example.android.bookdroid.databinding.ActivityAddRemoveShelvesBinding
import com.example.android.bookdroid.databinding.ActivityBooksBinding
import java.io.File

class BooksActivity : AppCompatActivity() {

    private lateinit var viewModel: BooksViewModel2
    private lateinit var binding: ActivityBooksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_books)

        val shelfWithBooks: ShelfWithBooks= intent.getParcelableExtra<ShelfWithBooks>(EXTRA_MESSAGE_SHELF_WITH_BOOKS) as ShelfWithBooks;

        binding.topAppBar.title = shelfWithBooks.shelf.name.replaceFirstChar {
            it.uppercaseChar();
        }

        binding.topAppBar.setNavigationOnClickListener {
            finish();
        }

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;
        val viewModelFactory = BooksViewModelFactory2(dataSource, shelfWithBooks);

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(BooksViewModel2::class.java);

        binding.books.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            adapter = BookAdapter(BookListener { book ->
                val uri = FileProvider.getUriForFile(this@BooksActivity, this@BooksActivity.getApplicationContext().getPackageName() + ".fileprovider", File(book.path));

                // Open file with user selected app
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(uri, "application/pdf")
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                startActivity(intent)
            })
        }

        binding.viewModel = viewModel;

        binding.lifecycleOwner = this;
    }
}