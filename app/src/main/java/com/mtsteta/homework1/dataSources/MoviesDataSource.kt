package com.mtsteta.homework1.dataSources

import com.mtsteta.homework1.database.entities.Movie

interface MoviesDataSource {
    fun getMovies(): List<Movie>
}