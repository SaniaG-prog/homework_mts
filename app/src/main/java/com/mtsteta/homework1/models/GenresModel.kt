package com.mtsteta.homework1.models

import com.mtsteta.homework1.dataSources.GenresDataSource

class GenresModel (private val genresDataSource: GenresDataSource) {
    fun getGenres() = genresDataSource.getGenres()
}