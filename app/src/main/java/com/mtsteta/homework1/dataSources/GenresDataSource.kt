package com.mtsteta.homework1.dataSources

import com.mtsteta.homework1.dto.GenreDto

interface GenresDataSource {
    fun getGenres(): List<GenreDto>
}