/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.bookdroid

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.bookdroid.database.*
import com.example.android.bookdroid.home.DownloadableBookAdapter
import com.example.android.bookdroid.home.DownloadableBookApiStatus
import com.example.android.bookdroid.home.DownloadableBookApiStatus.*
import com.example.android.bookdroid.library.BookAdapter
import com.example.android.bookdroid.library.BookWithShelvesAdapter
import com.example.android.bookdroid.library.ShelfAdapter
import com.example.android.bookdroid.library.ShelfWithBooksAdapter
import com.example.android.bookdroid.network.DownloadableBook
import com.example.android.bookdroid.wishlist.WishListAdapter
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView1(recyclerView: RecyclerView, data: List<DownloadableBook>?) {
    val adapter = recyclerView.adapter as DownloadableBookAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<BookWithShelves>?) {
    val adapter = recyclerView.adapter as BookWithShelvesAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerView3(recyclerView: RecyclerView, data: List<ShelfWithBooks>?) {
    val adapter = recyclerView.adapter as ShelfWithBooksAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerView4(recyclerView: RecyclerView, data: List<Shelf>?) {
    val adapter = recyclerView.adapter as ShelfAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerView5(recyclerView: RecyclerView, data: List<Book>?) {
    val adapter = recyclerView.adapter as BookAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerView6(recyclerView: RecyclerView, data: List<Wish>?) {
    val adapter = recyclerView.adapter as WishListAdapter
    adapter.submitList(data)
}

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl == null) {
        imgView.setImageResource(R.drawable.ic_baseline_image_not_supported_24);
    } else {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_baseline_broken_image_24))
            .into(imgView)
    }
}

/**
 * This binding adapter displays the [MarsApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: DownloadableBookApiStatus?) {
    when (status) {
        LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_cloud_off_24)
        }
        DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
    }
}

@BindingAdapter("listLinks")
fun bindText(txtView: TextView, listMaps: List<Map<String, String>>?) {
        if (listMaps != null) {
            for (listMap in listMaps) {
                txtView.append(listMap.get("title") + ": \n");
                txtView.append(listMap.get("url") + "\n");
            }
        }
}
