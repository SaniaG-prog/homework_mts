package com.mtsteta.homework1.listeners

import com.mtsteta.homework1.database.entities.Movie

interface MovieItemClickListener {
    fun onMovieClick(movie: Movie)
}