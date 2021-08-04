package com.mtsteta.homework1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtsteta.homework1.dataSourceImpls.GenresDataSourceImpl
import com.mtsteta.homework1.dataSourceImpls.MoviesDataSourceImpl
import com.mtsteta.homework1.dto.GenreDto
import com.mtsteta.homework1.dto.MovieDto
import com.mtsteta.homework1.models.GenresModel
import com.mtsteta.homework1.models.MoviesModel

class MyViewModel: ViewModel() {
    private val moviesMovel = MoviesModel(MoviesDataSourceImpl())
    private val genresModel = GenresModel(GenresDataSourceImpl())

    val moviesDataList: LiveData<List<MovieDto>> get() = _moviesDataList
    private val _moviesDataList = MutableLiveData<List<MovieDto>>()

    val genresDataList: LiveData<List<GenreDto>> get() = _genresDataList
    private val _genresDataList = MutableLiveData<List<GenreDto>>()

    fun loadMovies() {
        _moviesDataList.postValue(moviesMovel.getMovies())
    }

    fun updateMovies() {
        _moviesDataList.postValue(moviesMovel.getMovies().shuffled())
    }

    fun loadGenres() {
        _genresDataList.postValue(genresModel.getGenres())
    }

    fun loadData() {
        loadMovies()
        loadGenres()
    }
}