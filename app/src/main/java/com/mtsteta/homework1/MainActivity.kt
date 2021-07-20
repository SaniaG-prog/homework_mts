package com.mtsteta.homework1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework1.adapters.GenresAdapter
import com.mtsteta.homework1.adapters.MoviesAdapter
import com.mtsteta.homework1.dataSourceImpls.GenresDataSourceImpl
import com.mtsteta.homework1.dataSourceImpls.MoviesDataSourceImpl
import com.mtsteta.homework1.diffUtils.MovieDiffUtilCallback
import com.mtsteta.homework1.dto.GenreDto
import com.mtsteta.homework1.dto.MovieDto
import com.mtsteta.homework1.listeners.GenreItemClickListener
import com.mtsteta.homework1.listeners.MovieItemClickListener
import com.mtsteta.homework1.models.GenresModel
import com.mtsteta.homework1.models.MoviesModel


class MainActivity : AppCompatActivity(), MovieItemClickListener, GenreItemClickListener {
    private lateinit var updateButton: Button
    private lateinit var recyclerViewForMovies: RecyclerView
    private lateinit var recyclerViewForGenres: RecyclerView
    private val moviesModel = MoviesModel(MoviesDataSourceImpl())
    private val genresModel = GenresModel(GenresDataSourceImpl())
    private val adapterForMovies = MoviesAdapter(this)
    private val adapterForGenres = GenresAdapter(this, genresModel.getGenres())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        updateButton = findViewById(R.id.movie_list_button_for_list_update)
        updateButton.setOnClickListener {
            onUpdateButtonClick()
        }

        recyclerViewForMovies = findViewById(R.id.movie_list_recycler_view_for_movies)
        recyclerViewForGenres = findViewById(R.id.movie_list_recycler_view_for_genres)

        adapterForMovies.setData(moviesModel.getMovies())
        recyclerViewForMovies.adapter = adapterForMovies
        recyclerViewForMovies.layoutManager = GridLayoutManager(this, 2)

        recyclerViewForGenres.adapter = adapterForGenres
        recyclerViewForGenres.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onMovieClick(movieName: String) {
        Toast.makeText(this, movieName, Toast.LENGTH_SHORT).show()
    }

    override fun onGenreClick(genreName: String) {
        Toast.makeText(this, genreName, Toast.LENGTH_SHORT).show()
    }

    private fun onUpdateButtonClick() {
        val newList: MutableList<MovieDto> = MoviesModel(MoviesDataSourceImpl())
                .getMovies().toMutableList()
        newList.removeAt(1)
        newList.add(1, MovieDto(
                title = "Мортал Комбат",
                description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
                rateScore = 3,
                ageRestriction = 18,
                imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg"
        ))

        val movieDiffUtilCallback = MovieDiffUtilCallback(adapterForMovies.getData(), newList.toList())
        val movieDiffResult = DiffUtil.calculateDiff(movieDiffUtilCallback)

        adapterForMovies.setData(newList)
        movieDiffResult.dispatchUpdatesTo(adapterForMovies)
    }
}