package com.mtsteta.homework1

class MoviesModel (private val moviesDataSource: MoviesDataSource) {
    fun getMovies() = moviesDataSource.getMovies()
}