package com.example.android.bookdroid

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://openlibrary.org/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface BookApiService {
    /**
     * Returns a Coroutine [List] of [MarsProperty] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("api/books?bibkeys=ISBN:0201558025,ISBN:160078030X,ISBN:9781600780301&jscmd=data&format=json")
    fun getArtBooks(): Call<String>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object BookApi {
    val retrofitService : BookApiService by lazy { retrofit.create(BookApiService::class.java) }
    fun moshiService(): Moshi? {return moshi};
}