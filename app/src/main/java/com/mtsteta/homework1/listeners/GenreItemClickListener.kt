package com.mtsteta.homework1.listeners

import com.mtsteta.homework1.database.entities.Genre

interface GenreItemClickListener {
    fun onGenreClick(genre: Genre)
}