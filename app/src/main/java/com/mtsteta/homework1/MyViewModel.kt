package com.mtsteta.homework1

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtsteta.homework1.dataSourceImpls.GenresDataSourceImpl
import com.mtsteta.homework1.dataSourceImpls.MoviesDataSourceImpl
import com.mtsteta.homework1.database.AppDatabase
import com.mtsteta.homework1.database.entities.Movie
import com.mtsteta.homework1.dto.GenreDto
import com.mtsteta.homework1.models.GenresModel
import com.mtsteta.homework1.models.MoviesModel

class MyViewModel: ViewModel() {
    private val moviesMovel = MoviesModel(MoviesDataSourceImpl())
    private val genresModel = GenresModel(GenresDataSourceImpl())

    private var database: AppDatabase? = null

    val moviesDataList: LiveData<List<Movie>> get() = _moviesDataList
    private val _moviesDataList = MutableLiveData<List<Movie>>()

    val genresDataList: LiveData<List<GenreDto>> get() = _genresDataList
    private val _genresDataList = MutableLiveData<List<GenreDto>>()

    fun initDatabase(context: Context) {
        AppDatabase.initDatabase(context)
        database = AppDatabase.getInstance()!!
    }

    fun postMovies() {
        _moviesDataList.postValue(database?.movieDao()?.getAll())
    }

    fun loadMovies() {
        database?.movieDao()?.insertAll(moviesMovel.getMovies())
        postMovies()
    }

    fun updateMovies() {
        database?.movieDao()?.insertAll(moviesMovel.getMovies().shuffled())
        postMovies()
    }

    fun loadGenres() {
        _genresDataList.postValue(genresModel.getGenres())
    }

    fun loadData() {
        loadMovies()
        loadGenres()
    }
}