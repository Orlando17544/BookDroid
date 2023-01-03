package com.example.android.bookdroid.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DownloadableBook (
    var isbn: String?,
    val cover: Map<String, String>?,
    val title: String?,
    @Json(name = "publish_date") val publishDate: String?,
    @Json(name = "number_of_pages") val numberPages: Int?,
    val publishers: List<Map<String, String>>?,
    val links: List<Map<String, String>>?,
    val weight: String?,
    val url: String?,
    val subjects: List<Map<String, String>>?,
    val authors: List<Map<String, String>>?,
    @Json(name = "publish_places") val publishPlaces: List<Map<String, String>>?
        ) : Parcelable