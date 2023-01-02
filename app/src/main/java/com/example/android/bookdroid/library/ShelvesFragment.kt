package com.example.android.bookdroid.library

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.R
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.databinding.AddShelfBinding
import com.example.android.bookdroid.databinding.FragmentHomeBinding
import com.example.android.bookdroid.databinding.FragmentShelvesBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.io.File

const val EXTRA_MESSAGE_SHELF_WITH_BOOKS = "com.example.bookdroid.shelf_with_books";

class ShelvesFragment : Fragment() {

    private lateinit var viewModel: ShelvesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentShelvesBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application;

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;
        val viewModelFactory = ShelvesViewModelFactory(dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ShelvesViewModel::class.java)

        binding.viewModel = viewModel;

        binding.lifecycleOwner = viewLifecycleOwner;

        binding.shelvesWithBooks.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            adapter = ShelfWithBooksAdapter(ShelfWithBooksListener { shelfWithBooks ->

                val intent = Intent(this.context, BooksActivity::class.java).apply {
                    putExtra(EXTRA_MESSAGE_SHELF_WITH_BOOKS, shelfWithBooks);
                }

                startActivity(intent);
            }, ShelfWithBooksListener { shelfWithBooks ->
                /*
                val modalBottomSheet = OptionsModalBottomSheet()

                val args = Bundle();
                args.putParcelable(EXTRA_MESSAGE_BOOK, book);

                modalBottomSheet.arguments = args;

                modalBottomSheet.show(childFragmentManager, OptionsModalBottomSheet.TAG)*/
            })
        }

        binding.addShelf.setOnClickListener(View.OnClickListener {
            val addShelfView = inflater.inflate(R.layout.add_shelf, null)

            MaterialAlertDialogBuilder(it.context)
                .setView(addShelfView)
                .setNegativeButton("Cancel") { dialog, which ->
                    Snackbar.make(it, "The shelf was not added", Snackbar.LENGTH_SHORT).show();
                }
                .setPositiveButton("Ok") { dialog, which ->
                    val newShelfEditText = addShelfView.findViewById<TextInputEditText>(R.id.shelf_name);

                    if (newShelfEditText.text?.length?.equals(0)!!) {
                        Snackbar.make(this.requireView(), "The shelf wasn't added because it can't be empty", Snackbar.LENGTH_SHORT).show();
                    } else {
                        viewModel.addShelf(newShelfEditText.text.toString());
                    }
                }
                .show();
        })

        return binding.root;
    }
}