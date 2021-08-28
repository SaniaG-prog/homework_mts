package com.mtsteta.homework1.listeners

import android.view.View
import com.mtsteta.homework1.database.entities.Movie

interface MovieItemClickListener {
    fun onMovieClick(view: View, movie: Movie)
}