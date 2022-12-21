package com.example.android.bookdroid.network

import com.squareup.moshi.Json

data class DownloadableBook (
    var isbn: String?,
    val cover: Map<String, String>?,
    val title: String?,
    @Json(name = "publish_date") val publishDate: String?,
    @Json(name = "number_of_pages") val numberPages: Int?
        )