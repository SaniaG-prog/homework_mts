package com.mtsteta.homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MovieItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val recyclerView: RecyclerView = findViewById(R.id.movie_list_recycler_view)
        val moviesModel = MoviesModel(MoviesDataSourceImpl())
        val movies: List<MovieDto> = moviesModel.getMovies()
        val adapter = MoviesAdapter(this, movies)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onClick(movieName: String) {
        Toast.makeText(this, movieName, Toast.LENGTH_SHORT).show()
    }
}