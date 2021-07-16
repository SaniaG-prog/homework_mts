package com.mtsteta.homework1.models

import com.mtsteta.homework1.dataSources.MoviesDataSource

class MoviesModel (private val moviesDataSource: MoviesDataSource) {
    fun getMovies() = moviesDataSource.getMovies()
}