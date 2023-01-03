package com.example.android.bookdroid.wishlist

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bookdroid.R
import com.example.android.bookdroid.database.BookDatabase
import com.example.android.bookdroid.database.ShelfWithBooks
import com.example.android.bookdroid.database.Wish
import com.example.android.bookdroid.databinding.FragmentBooksBinding
import com.example.android.bookdroid.databinding.FragmentWishListBinding
import com.example.android.bookdroid.home.DownloadableBookActivity
import com.example.android.bookdroid.library.*
import java.io.File

const val EXTRA_MESSAGE_WISH = "com.example.bookdroid.wish";

class WishListFragment : Fragment() {

    private lateinit var viewModel: WishListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentWishListBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application;

        val dataSource = BookDatabase.getInstance(application).bookDatabaseDao;
        val viewModelFactory = WishListViewModelFactory(dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            ViewModelProvider(
                this, viewModelFactory).get(WishListViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner;

        binding.viewModel = viewModel;

        binding.wishList.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false);
            adapter = WishListAdapter(WishListener { wish, options ->

                val intent = Intent(context, DownloadableBookActivity::class.java).apply {
                    viewModel.getBookByBookId(wish.isbn).observe(viewLifecycleOwner, Observer{
                        putExtra(EXTRA_MESSAGE_BOOK, it);
                    })
                }
                startActivity(intent);

            }, WishListener { wish, options ->

                showMenu(options, R.menu.wish_options_menu, wish)

            })
        }

        return binding.root;
    }

    private fun showMenu(v: View?, @MenuRes menuRes: Int, wish: Wish) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            // Respond to menu item click.
            viewModel.deleteWish(wish);

            return@setOnMenuItemClickListener true;
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }
}