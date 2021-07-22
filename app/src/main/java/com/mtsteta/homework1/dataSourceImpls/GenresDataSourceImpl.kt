package com.mtsteta.homework1.dataSourceImpls

import com.mtsteta.homework1.dataSources.GenresDataSource
import com.mtsteta.homework1.dto.GenreDto

class GenresDataSourceImpl:GenresDataSource {
    override fun getGenres(): List<GenreDto> = listOf(
            GenreDto("боевики"), GenreDto("приключения"),
            GenreDto("мультики"), GenreDto("комедии"),
            GenreDto("криминал"), GenreDto("документальные"),
            GenreDto("драмы"), GenreDto("семейные"),
            GenreDto("фантастика"), GenreDto("исторические"),
            GenreDto("ужасы"), GenreDto("музыка"),
            GenreDto("мистика"), GenreDto("романтика"),
            GenreDto("научная фантастика"), GenreDto("триллеры"),
            GenreDto("военные"), GenreDto("вестерны"),
            GenreDto("реальное ТВ"))
}