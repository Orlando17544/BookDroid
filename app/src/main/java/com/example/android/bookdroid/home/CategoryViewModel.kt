package com.example.android.bookdroid.home

import android.os.Parcelable
import androidx.lifecycle.*
import com.example.android.bookdroid.BookApi
import com.example.android.bookdroid.network.DownloadableBook
import com.squareup.moshi.Types
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class CategoryViewModel(
    val downloadableBooksParcelable: List<Parcelable>?, val category: String
) : ViewModel() {

    private var _downloadableBooks = MutableLiveData<List<DownloadableBook>>();

    val downloadableBooks: LiveData<List<DownloadableBook>>
        get() = _downloadableBooks

    private val _downloadableBookStatus = MutableLiveData<DownloadableBookApiStatus>()

    val downloadableBookStatus: LiveData<DownloadableBookApiStatus>
        get() = _downloadableBookStatus

    private val _navigateToSelectedDownloadableBook = MutableLiveData<DownloadableBook>();

    val navigateToSelectedDownloadableBook: LiveData<DownloadableBook>
        get() = _navigateToSelectedDownloadableBook

    init {
        viewModelScope.launch {
            if (categoryBooksEmpty()) {
                when (category) {
                    "fun" -> getFunBooksFromApi();
                    "self help" -> getSelfHelpBooksFromApi();
                    "biography" -> getBiographyBooksFromApi();
                    "memories" -> getMemoriesBooksFromApi();
                    "science fiction" -> getScienceFictionBooksFromApi();
                    "fantasy" -> getFantasyBooksFromApi();
                    "literature" -> getLiteratureBooksFromApi();
                    "finance" -> getFinanceBooksFromApi();
                    "investment" -> getInvestmentBooksFromApi();
                    "history" -> getHistoryBooksFromApi();
                    "computing" -> getComputingBooksFromApi();
                    "technology" -> getTechnologyBooksFromApi();
                    "children's books" -> getChildrenBooksFromApi();
                    "mystery" -> getMysteryBooksFromApi();
                    "suspense" -> getSuspenseBooksFromApi();
                    "romance novel" -> getRomanceNovelBooksFromApi();
                    "spirituality" -> getSpiritualityBooksFromApi();
                    "health" -> getHealthBooksFromApi();
                    "lifestyle" -> getLifeStyleBooksFromApi();
                }
            } else {
                castToDownloadableBooks();
            }
        }
    }

    fun displayDownloadableBook(downloadableBook: DownloadableBook) {
        _navigateToSelectedDownloadableBook.value = downloadableBook;
    }

    private fun castToDownloadableBooks() {
        val downloadableBooks = mutableSetOf<DownloadableBook>();

        downloadableBooksParcelable?.let {
            for (downloadableBookParcelable in downloadableBooksParcelable) {
                downloadableBooks.add(downloadableBookParcelable as DownloadableBook);
            }
            _downloadableBooks.value = downloadableBooks.toList();
        }
    }

    private fun categoryBooksEmpty() : Boolean {
        if (downloadableBooksParcelable == null) {
            return true;
        } else {
            return false;
        }
    }

    private fun getFunBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getFunBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getSelfHelpBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getSelfHelpBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getBiographyBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getBiographyBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getMemoriesBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getMemoriesBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getScienceFictionBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getScienceFictionBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getFantasyBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getFantasyBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getLiteratureBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getLiteratureBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getFinanceBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getFinanceBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getInvestmentBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getInvestmentBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getHistoryBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getHistoryBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getComputingBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getComputingBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getTechnologyBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getTechnologyBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getChildrenBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getChildrenBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getMysteryBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getMysteryBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getSuspenseBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getSuspenseBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getRomanceNovelBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getRomanceNovelBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getSpiritualityBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getSpiritualityBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getHealthBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getHealthBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }

    private fun getLifeStyleBooksFromApi() {
        viewModelScope.launch {
            _downloadableBookStatus.value = DownloadableBookApiStatus.LOADING
            BookApi.retrofitService.getLifeStyleBooks().enqueue(
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

                        _downloadableBooks.value = values;
                        _downloadableBookStatus.value = DownloadableBookApiStatus.DONE;
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        println("Failure" + t.message);
                        _downloadableBookStatus.value = DownloadableBookApiStatus.ERROR
                        _downloadableBooks.value = ArrayList();
                    }
                }
            );
        }
    }
}