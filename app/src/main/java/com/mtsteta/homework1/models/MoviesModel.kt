package com.mtsteta.homework1.models

import android.util.Log
import com.mtsteta.homework1.App
import com.mtsteta.homework1.PopularMoviesResponce
import com.mtsteta.homework1.database.entities.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesModel () {
    fun getPopularMovies(): List<Movie> {
        var popularMovies: List<Movie> = emptyList()
        App.instance.apiService.getPopularMovies().enqueue(object: Callback<PopularMoviesResponce>
        {
            override fun onResponse(
                call: Call<PopularMoviesResponce>,
                response: Response<PopularMoviesResponce>
            ) {
                Log.e("Retrofit2", response.body()?.results.toString())
                popularMovies = response.body()?.results!!
            }

            override fun onFailure(call: Call<PopularMoviesResponce>, t: Throwable) {
                Log.e("Retrofit2", "Fail")
            }
        })
        return popularMovies
    }
}