package com.mtsteta.homework1

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}