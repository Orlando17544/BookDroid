package com.example.android.bookdroid.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.BookApi
import com.example.android.bookdroid.network.DownloadableBook
import com.squareup.moshi.Types
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

enum class DownloadableBookApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    private val _educationBooks = MutableLiveData<List<DownloadableBook>>();

    val educationBooks: LiveData<List<DownloadableBook>>
        get() = _educationBooks

    private val _educationStatus = MutableLiveData<DownloadableBookApiStatus>()

    val educationStatus: LiveData<DownloadableBookApiStatus>
        get() = _educationStatus



    private val _fictionBooks = MutableLiveData<List<DownloadableBook>>();

    val fictionBooks: LiveData<List<DownloadableBook>>
        get() = _fictionBooks

    private val _fictionStatus = MutableLiveData<DownloadableBookApiStatus>()

    val fictionStatus: LiveData<DownloadableBookApiStatus>
        get() = _fictionStatus

    init {
        getEducationBooksFromApi();
        getFictionBooksFromApi();
    }

    fun getEducationBooksFromApi() {
        viewModelScope.launch {
            _educationStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getEducationBooks().enqueue(
                object: Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {

                        var jsonResponse: String = response.body().toString();

                        val type: Type = Types.newParameterizedType(Map::class.java, String::class.java, DownloadableBook::class.java);
                        val adapter = BookApi.moshiService()?.adapter<Map<String, DownloadableBook>>(type);
                        val booksMap: Map<String, DownloadableBook>? = adapter?.fromJson(jsonResponse);

                        val keys: MutableList<String>? = booksMap?.keys?.toMutableList()
                        val values: List<DownloadableBook>? = booksMap?.values?.toMutableList()

                        for (i in keys?.indices!!) {
                            keys[i] = keys[i].replace("ISBN:", "");
                            values?.get(i)?.isbn = keys[i].toLong();
                        }

                        _educationBooks.value = values;
                        _educationStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _educationStatus.value = DownloadableBookApiStatus.ERROR
                        _educationBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    fun getFictionBooksFromApi() {
        viewModelScope.launch {
            _fictionStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getFictionBooks().enqueue(
                object: Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {

                        var jsonResponse: String = response.body().toString();

                        val type: Type = Types.newParameterizedType(Map::class.java, String::class.java, DownloadableBook::class.java);
                        val adapter = BookApi.moshiService()?.adapter<Map<String, DownloadableBook>>(type);
                        val booksMap: Map<String, DownloadableBook>? = adapter?.fromJson(jsonResponse);

                        val keys: MutableList<String>? = booksMap?.keys?.toMutableList()
                        val values: List<DownloadableBook>? = booksMap?.values?.toMutableList()

                        for (i in keys?.indices!!) {
                            keys[i] = keys[i].replace("ISBN:", "");
                            values?.get(i)?.isbn = keys[i].toLong();
                        }

                        _fictionBooks.value = values;
                        _fictionStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _fictionStatus.value = DownloadableBookApiStatus.ERROR
                        _fictionBooks.value = ArrayList();
                    }
                }
            );
        }
    }
}