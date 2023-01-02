package com.example.android.bookdroid.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.R.layout.activity_add_remove_shelves
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.databinding.ActivityAddRemoveShelvesBinding

class AddRemoveShelvesActivity : AppCompatActivity() {

    private lateinit var viewModel: AddRemoveShelvesViewModel
    private lateinit var binding: ActivityAddRemoveShelvesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_book_shelf)
        binding = DataBindingUtil.setContentView(this, activity_add_remove_shelves)

        val shelfAction: String = intent.getStringExtra(EXTRA_MESSAGE_SHELF_ACTION) as String;
        var bookWithShelves: BookWithShelves = intent.getParcelableExtra<BookWithShelves>(EXTRA_MESSAGE_BOOK_WITH_SHELVES) as BookWithShelves;

        if (shelfAction.equals("remove")) {
            binding.topAppBar.title = "Remove the book from a shelf";
        } else {
            binding.topAppBar.title = "Add the book to a shelf";
        }

        binding.topAppBar.setNavigationOnClickListener {
            finish();
        }

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;
        val viewModelFactory = AddRemoveShelvesViewModelFactory(dataSource, bookWithShelves, shelfAction);

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(AddRemoveShelvesViewModel::class.java)

        binding.shelves.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            adapter = ShelfAdapter(ShelfListener { shelf ->
                viewModel.executeActionInBook(shelf);
                finish();
            })
        }

        binding.viewModel = viewModel;

        binding.lifecycleOwner = this;
    }
}