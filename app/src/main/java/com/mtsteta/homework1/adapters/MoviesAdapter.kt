package com.mtsteta.homework1.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mtsteta.homework1.listeners.MovieItemClickListener
import com.mtsteta.homework1.R
import com.mtsteta.homework1.database.entities.Movie
import com.mtsteta.homework1.diffUtils.MovieDiffUtilCallback

private const val ADULT_RESTRICTION_TRUE = "18+"
private const val ADULT_RESTRICTION_FALSE = "0+"
private const val MOVIE_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

class MoviesAdapter(private val listener: MovieItemClickListener):
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var movies: List<Movie> = emptyList()

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        private val name: TextView = itemView.findViewById(R.id.item_movie_name)
        private val description: TextView = itemView.findViewById(R.id.item_movie_description)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.item_movie_rating_bar)
        private val age: TextView = itemView.findViewById(R.id.item_movie_age)

        fun bind(movie: Movie) {
            poster.load(MOVIE_IMAGE_BASE_URL + movie.posterPath)
            name.text = movie.title
            if (movie.overview.isNotEmpty()) {
                if (movie.overview.length > 30) {
                    description.text = movie.overview.substring(0, 30) + "..."
                }
                else {
                    description.text = movie.overview + "..."
                }
            }
            else {
                description.text = "Описание скоро появится)"
            }
            ratingBar.rating = movie.voteAverage
            if (movie.adult) {
                age.text = ADULT_RESTRICTION_TRUE
            }
            else {
                age.text = ADULT_RESTRICTION_FALSE
            }

            poster.transitionName = movie.posterPath
            name.transitionName = movie.title
            description.transitionName = movie.overview
            ratingBar.transitionName = movie.voteAverage.toString()
            age.transitionName = movie.adult.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener {
            listener.onMovieClick(holder.itemView, movies.get(position))
        }
    }

    fun setData(newMovies: List<Movie>) {
        val movieDiffUtilCallback = MovieDiffUtilCallback(movies, newMovies)
        val movieDiffResult = DiffUtil.calculateDiff(movieDiffUtilCallback)
        movies = newMovies
        movieDiffResult.dispatchUpdatesTo(this)
    }

    fun getData(): List<Movie> {
        return movies
    }
}