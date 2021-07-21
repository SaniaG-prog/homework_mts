package com.mtsteta.homework1.listeners

import com.mtsteta.homework1.dto.MovieDto

interface MovieItemClickListener {
    fun onMovieClick(movie: MovieDto)
}