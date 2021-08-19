package com.mtsteta.homework1

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtsteta.homework1.dataSourceImpls.GenresDataSourceImpl
import com.mtsteta.homework1.database.entities.Movie
import com.mtsteta.homework1.dto.GenreDto
import com.mtsteta.homework1.models.GenresModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel: ViewModel() {
    private val genresModel = GenresModel(GenresDataSourceImpl())

    private val prefs: SharedPreferences by lazy {
        App.prefs!!
    }

    val moviesDataList: LiveData<List<Movie>> get() = _moviesDataList
    private val _moviesDataList = MutableLiveData<List<Movie>>()

    val genresDataList: LiveData<List<GenreDto>> get() = _genresDataList
    private val _genresDataList = MutableLiveData<List<GenreDto>>()

    fun addPairToPrefs(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getValueByKeyInPrefs(key: String): String {
        return prefs.getString(key, "")!!
    }

    fun initDatabase() {
        if (App.database?.movieDao()?.getAll()?.size == 0) {
            viewModelScope.launch {
                val popularMovies: List<Movie> = withContext(Dispatchers.IO) {
                    App.instance.apiService.getPopularMovies().results
                }
                App.database?.movieDao()?.insertAll(popularMovies)
            }
        }
    }

    fun loadMovies() {
        _moviesDataList.postValue(App.database?.movieDao()?.getAll())
        Log.d("Loading movies", App.database?.movieDao()?.getAll().toString())
    }

    fun updateMovies() {
        _moviesDataList.postValue(App.database?.movieDao()?.getAll()?.shuffled())
    }

    fun loadGenres() {
        _genresDataList.postValue(genresModel.getGenres())
    }

    fun loadData() {
        loadMovies()
        loadGenres()
    }
}