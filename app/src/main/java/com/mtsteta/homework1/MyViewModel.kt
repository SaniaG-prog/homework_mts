package com.mtsteta.homework1

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtsteta.homework1.database.entities.Actor
import com.mtsteta.homework1.database.entities.Genre
import com.mtsteta.homework1.database.entities.Movie
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val prefs: SharedPreferences by lazy {
        App.prefs!!
    }

    val moviesDataList: LiveData<List<Movie>> get() = _moviesDataList
    private val _moviesDataList = MutableLiveData<List<Movie>>()

    val genresDataList: LiveData<List<Genre>> get() = _genresDataList
    private val _genresDataList = MutableLiveData<List<Genre>>()

    val actorsDataList: LiveData<List<Actor>> get() = _actorsDataList
    private val _actorsDataList = MutableLiveData<List<Actor>>()

    fun addPairToPrefs(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getValueByKeyInPrefs(key: String): String {
        return prefs.getString(key, "")!!
    }

    fun loadMovies() {
        _moviesDataList.postValue(App.database?.movieDao()?.getAll())
    }

    fun updateMovies() {
        _moviesDataList.postValue(App.database?.movieDao()?.getAll()?.shuffled())
    }

    fun loadGenres() {
        _genresDataList.postValue(App.database?.genreDao()?.getAll())
    }

    fun loadActors(movieId: Long) {
        viewModelScope.launch {
            _actorsDataList.postValue(App.instance.apiService.getActors(movieId).cast)
        }
    }

    fun getGenreNameById(genreId: Int): String {
        return App.database?.genreDao()?.getById(genreId)?.name ?: ""
    }

    fun loadData() {
        loadMovies()
        loadGenres()
    }
}