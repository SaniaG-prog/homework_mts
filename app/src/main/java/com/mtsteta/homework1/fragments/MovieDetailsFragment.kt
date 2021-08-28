package com.mtsteta.homework1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mtsteta.homework1.MyViewModel
import com.mtsteta.homework1.R
import com.mtsteta.homework1.adapters.ActorsAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import java.util.concurrent.TimeUnit

private const val MOVIE_ID = "movieId"
private const val MOVIE_NAME = "movieName"
private const val MOVIE_DESCRIPTION = "movieDescription"
private const val MOVIE_STAR_NUMBER = "movieStarNumber"
private const val MOVIE_AGE = "movieAge"
private const val MOVIE_IMAGE_URL = "movieImageUrl"
private const val MOVIE_DATE = "movieDate"
private const val ADULT_RESTRICTION_TRUE = "18+"
private const val ADULT_RESTRICTION_FALSE = "0+"
private const val MOVIE_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

class MovieDetailsFragment : Fragment() {
    private var movieId: Long? = null
    private var movieName: String? = null
    private var movieDescription: String? = null
    private var movieStarNumber: Float? = null
    private var movieAge: Boolean = false
    private var movieImageUrl: String? = null
    private var movieDate: String? = null

    private lateinit var moviePoster: ImageView
    private lateinit var movieDateTextView: TextView
    private lateinit var movieNameTextView: TextView
    private lateinit var movieDescriptionTextView: TextView
    private lateinit var movieAgeTextView: TextView
    private lateinit var movieRatingBar: RatingBar
    private lateinit var recyclerViewForActors: RecyclerView

    private val myViewModel: MyViewModel by viewModels()
    private val adapterForActors = ActorsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            movieId = it.getLong(MOVIE_ID)
            movieName = it.getString(MOVIE_NAME)
            movieDescription = it.getString(MOVIE_DESCRIPTION)
            movieStarNumber = it.getFloat(MOVIE_STAR_NUMBER)
            movieAge = it.getBoolean(MOVIE_AGE)
            movieImageUrl = it.getString(MOVIE_IMAGE_URL)
            movieDate = it.getString(MOVIE_DATE)
        }

        myViewModel.actorsDataList.observe(this, Observer(adapterForActors::setData))
        myViewModel.loadActors(movieId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviePoster = view.findViewById(R.id.movie_details_film_poster)
        movieDateTextView = view.findViewById(R.id.movie_details_date)
        movieNameTextView = view.findViewById(R.id.movie_details_name)
        movieDescriptionTextView = view.findViewById(R.id.movie_details_film_description)
        movieAgeTextView = view.findViewById(R.id.movie_details_age_limit)
        movieRatingBar = view.findViewById(R.id.movie_details_rating_bar)
        recyclerViewForActors = view.findViewById(R.id.movie_details_recycler_view_for_actors)

        moviePoster.load(MOVIE_IMAGE_BASE_URL + movieImageUrl)
        movieDateTextView.text = movieDate
        movieNameTextView.text = movieName
        movieDescriptionTextView.text = movieDescription
        if (movieAge == true) {
            movieAgeTextView.text = ADULT_RESTRICTION_TRUE
        }
        else {
            movieAgeTextView.text = ADULT_RESTRICTION_FALSE
        }
        movieRatingBar.rating = movieStarNumber!!.toFloat()

        moviePoster.transitionName = movieImageUrl
        movieNameTextView.transitionName = movieName
        movieDescriptionTextView.transitionName = movieDescription?.substring(0, 10)
        movieRatingBar.transitionName = movieStarNumber.toString()
        movieAgeTextView.transitionName = movieAge.toString()

        recyclerViewForActors.adapter = adapterForActors
        recyclerViewForActors.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            MovieDetailsFragment().apply {
                arguments = bundle
            }
    }
}