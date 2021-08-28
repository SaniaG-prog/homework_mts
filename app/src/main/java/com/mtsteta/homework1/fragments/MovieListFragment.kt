package com.mtsteta.homework1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.transition.TransitionInflater
import com.mtsteta.homework1.MyViewModel
import com.mtsteta.homework1.R
import com.mtsteta.homework1.adapters.GenresAdapter
import com.mtsteta.homework1.adapters.MoviesAdapter
import com.mtsteta.homework1.database.entities.Genre
import com.mtsteta.homework1.database.entities.Movie
import com.mtsteta.homework1.listeners.GenreItemClickListener
import com.mtsteta.homework1.listeners.MovieItemClickListener
import jp.wasabeef.recyclerview.animators.*

private const val MOVIE_ID = "movieId"
private const val MOVIE_NAME = "movieName"
private const val MOVIE_DESCRIPTION = "movieDescription"
private const val MOVIE_STAR_NUMBER = "movieStarNumber"
private const val MOVIE_AGE = "movieAge"
private const val MOVIE_IMAGE_URL = "movieImageUrl"
private const val MOVIE_DATE = "movieDate"

class MovieListFragment() : Fragment(), MovieItemClickListener, GenreItemClickListener {
    private lateinit var recyclerViewForMovies: RecyclerView
    private lateinit var recyclerViewForGenres: RecyclerView
    private lateinit var pullToRefreshLayout: SwipeRefreshLayout
    private lateinit var navController: NavController

    private val myViewModel: MyViewModel by viewModels()
    private val adapterForMovies = MoviesAdapter(this)
    private val adapterForGenres = GenresAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myViewModel.moviesDataList.observe(this, Observer (adapterForMovies::setData))
        myViewModel.genresDataList.observe(this, Observer (adapterForGenres::setData))
        myViewModel.loadData()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullToRefreshLayout = view.findViewById(R.id.movie_list_refresh_layout)
        pullToRefreshLayout.setOnRefreshListener {
            myViewModel.updateMovies()
            pullToRefreshLayout.isRefreshing = false
        }

        navController = view.findNavController()
        recyclerViewForMovies = view.findViewById(R.id.movie_list_recycler_view_for_movies)
        recyclerViewForGenres = view.findViewById(R.id.movie_list_recycler_view_for_genres)

        recyclerViewForMovies.adapter = adapterForMovies
        recyclerViewForMovies.itemAnimator = LandingAnimator()
        recyclerViewForMovies.layoutManager = GridLayoutManager(requireContext(), 2)

        recyclerViewForGenres.adapter = adapterForGenres
        recyclerViewForGenres.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)

        postponeEnterTransition()
        recyclerViewForMovies.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onGenreClick(genre: Genre) {
        genre.isInterestng = true
        myViewModel.updateGenreInDb(genre)
    }

    override fun onMovieClick(view:View, movie: Movie) {
        val bundle = Bundle()
        bundle.putLong(MOVIE_ID, movie.id)
        bundle.putString(MOVIE_NAME, movie.title)
        bundle.putString(MOVIE_DESCRIPTION, movie.overview)
        bundle.putFloat(MOVIE_STAR_NUMBER, movie.voteAverage)
        bundle.putBoolean(MOVIE_AGE, movie.adult)
        bundle.putString(MOVIE_IMAGE_URL, movie.posterPath)
        bundle.putString(MOVIE_DATE, movie.releaseDate)

        val extras = FragmentNavigatorExtras(
            view.findViewById<ImageView>(R.id.item_movie_poster) to movie.posterPath,
            view.findViewById<TextView>(R.id.item_movie_name) to movie.title,
            view.findViewById<TextView>(R.id.item_movie_description) to movie.overview,
            view.findViewById<RatingBar>(R.id.item_movie_rating_bar) to movie.voteAverage.toString(),
            view.findViewById<TextView>(R.id.item_movie_age) to movie.adult.toString()
        )

        navController.navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle, null, extras)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}