package com.example.android.bookdroid.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.database.BookDatabaseDao
import com.example.android.bookdroid.database.Wish
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishListViewModel(val database: BookDatabaseDao) : ViewModel() {
    val wishList : LiveData<List<Wish>> = database.getWishList();

    fun getBookByBookId(isbn: Long): MutableLiveData<Book?> {
        val result = MutableLiveData<Book?>();
        viewModelScope.launch {
            val book = database.getBookByIsbn(isbn);

            result.value = book;
        }
        return result;
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.deleteWish(wish);
            }
        }
    }
}