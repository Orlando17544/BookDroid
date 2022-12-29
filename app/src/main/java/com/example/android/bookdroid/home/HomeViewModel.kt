package com.example.android.bookdroid.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.bookdroid.BookApi
import com.example.android.bookdroid.database.Book
import com.example.android.bookdroid.network.DownloadableBook
import com.squareup.moshi.Types
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

enum class DownloadableBookApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {
    //val emptyList = listOf<DownloadableBook>();

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

    private val _artBooks = MutableLiveData<List<DownloadableBook>>();

    val artBooks: LiveData<List<DownloadableBook>>
        get() = _artBooks

    private val _artStatus = MutableLiveData<DownloadableBookApiStatus>()

    val artStatus: LiveData<DownloadableBookApiStatus>
        get() = _artStatus

    private val _religionBooks = MutableLiveData<List<DownloadableBook>>();

    val religionBooks: LiveData<List<DownloadableBook>>
        get() = _religionBooks

    private val _religionStatus = MutableLiveData<DownloadableBookApiStatus>()

    val religionStatus: LiveData<DownloadableBookApiStatus>
        get() = _religionStatus

    private val _navigateToSelectedCategory = MutableLiveData<Pair<List<DownloadableBook>?, String?>>();

    val navigateToSelectedCategory: LiveData<Pair<List<DownloadableBook>?, String?>>
        get() = _navigateToSelectedCategory

    private val _navigateToSelectedDownloadableBook = MutableLiveData<DownloadableBook>();

    val navigateToSelectedDownloadableBook: LiveData<DownloadableBook>
        get() = _navigateToSelectedDownloadableBook

    init {
        getEducationBooksFromApi();
        getFictionBooksFromApi();
        getArtBooksFromApi();
        getReligionBooksFromApi();
    }

    fun displayCategoryBooks(downloadableBooks: List<DownloadableBook>?, category: String?) {
        _navigateToSelectedCategory.value = Pair(downloadableBooks, category);
    }

    fun displayDownloadableBook(downloadableBook: DownloadableBook) {
        _navigateToSelectedDownloadableBook.value = downloadableBook;
    }

    private fun getEducationBooksFromApi() {
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

    private fun getFictionBooksFromApi() {
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

    private fun getArtBooksFromApi() {
        viewModelScope.launch {
            _artStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getArtBooks().enqueue(
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

                        _artBooks.value = values;
                        _artStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _artStatus.value = DownloadableBookApiStatus.ERROR
                        _artBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getReligionBooksFromApi() {
        viewModelScope.launch {
            _religionStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getReligionBooks().enqueue(
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

                        _religionBooks.value = values;
                        _religionStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _religionStatus.value = DownloadableBookApiStatus.ERROR
                        _religionBooks.value = ArrayList();
                    }
                }
            );
        }
    }
}