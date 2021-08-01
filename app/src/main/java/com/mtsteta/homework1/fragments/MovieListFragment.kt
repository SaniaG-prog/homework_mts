package com.mtsteta.homework1.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mtsteta.homework1.R
import com.mtsteta.homework1.adapters.GenresAdapter
import com.mtsteta.homework1.adapters.MoviesAdapter
import com.mtsteta.homework1.dataSourceImpls.GenresDataSourceImpl
import com.mtsteta.homework1.dataSourceImpls.MoviesDataSourceImpl
import com.mtsteta.homework1.diffUtils.MovieDiffUtilCallback
import com.mtsteta.homework1.dto.MovieDto
import com.mtsteta.homework1.listeners.GenreItemClickListener
import com.mtsteta.homework1.listeners.MovieItemClickListener
import com.mtsteta.homework1.models.GenresModel
import com.mtsteta.homework1.models.MoviesModel
import kotlinx.coroutines.*

class MovieListFragment() : Fragment(), MovieItemClickListener, GenreItemClickListener {
    private lateinit var updateButton: Button
    private lateinit var recyclerViewForMovies: RecyclerView
    private lateinit var recyclerViewForGenres: RecyclerView
    private lateinit var pullToRefreshLayout: SwipeRefreshLayout
    private val moviesModel = MoviesModel(MoviesDataSourceImpl())
    private val genresModel = GenresModel(GenresDataSourceImpl())
    private val adapterForMovies = MoviesAdapter(this)
    private val adapterForGenres = GenresAdapter(this, genresModel.getGenres())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateButton = view.findViewById(R.id.movie_list_button_for_test_diff_utils)
        updateButton.setOnClickListener {
            onUpdateButtonClick()
        }

        pullToRefreshLayout = view.findViewById(R.id.movie_list_refresh_layout)
        pullToRefreshLayout.setOnRefreshListener {
            setDataForMovieAdapter()
            pullToRefreshLayout.isRefreshing = false
        }

        recyclerViewForMovies = view.findViewById(R.id.movie_list_recycler_view_for_movies)
        setDataForMovieAdapter()

        recyclerViewForGenres = view.findViewById(R.id.movie_list_recycler_view_for_genres)

        recyclerViewForMovies.adapter = adapterForMovies
        recyclerViewForMovies.layoutManager = GridLayoutManager(requireContext(), 2)

        recyclerViewForGenres.adapter = adapterForGenres
        recyclerViewForGenres.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
    }

    fun setDataForMovieAdapter() = runBlocking {
        setData()
    }

    suspend fun setData() = coroutineScope {
        val downloadAndSet: Job = launch {
            try {
                val movieList: List<MovieDto> = moviesModel.getMovies().shuffled()
                delay(400L)
                adapterForMovies.setData(movieList)
            }
            catch(e: CancellationException) {
                Log.e("Cotoutine", "Catch error")
                val movieList: List<MovieDto> = emptyList()
                Toast.makeText(requireContext(), "Нет фильмов для отображения", Toast.LENGTH_LONG)
                adapterForMovies.setData(movieList)
            }
        }
        downloadAndSet.join()
    }

    override fun onGenreClick(genreName: String) {
        Toast.makeText(requireContext(), genreName, Toast.LENGTH_SHORT).show()
    }

    private fun onUpdateButtonClick() {
        val newList: MutableList<MovieDto> = adapterForMovies.getData().toMutableList()
        newList.removeAt(1)
        newList.add(1, MovieDto(
            title = "Мортал Комбат",
            description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
            rateScore = 3,
            ageRestriction = 18,
            imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg"
        )
        )

        val movieDiffUtilCallback = MovieDiffUtilCallback(adapterForMovies.getData(), newList.toList())
        val movieDiffResult = DiffUtil.calculateDiff(movieDiffUtilCallback)

        adapterForMovies.setData(newList)
        movieDiffResult.dispatchUpdatesTo(adapterForMovies)
    }

    override fun onMovieClick(movie: MovieDto) {
        parentFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            MovieDetailsFragment.newInstance(movie)).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}