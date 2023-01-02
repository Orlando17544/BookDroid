package com.example.android.bookdroid.library

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.database.BookWithShelves
import com.example.android.bookdroid.databinding.OptionsModalBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

const val EXTRA_SHELF_ACTION = "com.example.bookdroid.shelf_action"

class OptionsModalBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: OptionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = OptionsModalBottomSheetBinding.inflate(inflater);

        val bookWithShelves: BookWithShelves = arguments?.getParcelable<BookWithShelves>(EXTRA_MESSAGE_BOOK_WITH_SHELVES) as BookWithShelves;

        val application = requireNotNull(this.activity).application;

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;

        val viewModelFactory = OptionsViewModelFactory(bookWithShelves, dataSource);

        // Get a reference to the ViewModel associated with this fragment.
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OptionsViewModel::class.java)

        binding.viewModel = viewModel;

        binding.lifecycleOwner = this;

        viewModel.close.observe(viewLifecycleOwner, Observer {
            /*val intent = Intent(context, AddBookShelfActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_BOOK, book);
            }
            startActivity(intent);*/
            dismiss()
        })

        viewModel.navigateToShelvesActivity.observe(viewLifecycleOwner, Observer { pair ->
            val intent = Intent(context, AddRemoveShelvesActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_BOOK_WITH_SHELVES, pair.first);
                putExtra(EXTRA_SHELF_ACTION, pair.second)
            }
            startActivity(intent);
            dismiss();
        })

        return binding.root;
    }
        //inflater.inflate(R.layout.options_modal_bottom_sheet, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}