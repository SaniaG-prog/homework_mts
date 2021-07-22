package com.mtsteta.homework1.dataSources

import com.mtsteta.homework1.dto.MovieDto

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}