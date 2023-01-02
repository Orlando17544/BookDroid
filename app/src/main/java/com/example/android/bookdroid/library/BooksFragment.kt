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
import com.example.android.bookdroid.databinding.FragmentBooksBinding
import com.example.android.bookdroid.databinding.FragmentLibraryBinding
import com.example.android.bookdroid.home.DownloadableBookAdapter
import com.example.android.bookdroid.home.DownloadableBookListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File

const val EXTRA_MESSAGE_BOOK_WITH_SHELVES = "com.example.bookdroid.book_with_shelves"

class BooksFragment : Fragment() {

    private lateinit var viewModel: BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBooksBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application;

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;
        val viewModelFactory = BooksViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(BooksViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner;

        binding.viewModel = viewModel;

        binding.books.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            adapter = BookWithShelvesAdapter(BookWithShelvesListener { bookWithShelves ->
                val uri = FileProvider.getUriForFile(context, application.applicationContext.getPackageName() + ".fileprovider", File(bookWithShelves.book.path));

                // Open file with user selected app
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(uri, "application/pdf")
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                startActivity(intent)
            }, BookWithShelvesListener { bookWithShelves ->
                val modalBottomSheet = OptionsModalBottomSheet()

                val args = Bundle();
                args.putParcelable(EXTRA_MESSAGE_BOOK_WITH_SHELVES, bookWithShelves);

                modalBottomSheet.arguments = args;

                modalBottomSheet.show(childFragmentManager, OptionsModalBottomSheet.TAG)
            })
        }

        return binding.root;
    }
}