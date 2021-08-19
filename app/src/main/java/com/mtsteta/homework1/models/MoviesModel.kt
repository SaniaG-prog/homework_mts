package com.mtsteta.homework1.models

import android.util.Log
import com.mtsteta.homework1.App
import com.mtsteta.homework1.PopularMoviesResponce
import com.mtsteta.homework1.database.entities.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesModel () {
    fun getPopularMovies() {
        App.instance.apiService.getPopularMovies().enqueue(object: Callback<PopularMoviesResponce>
        {
            override fun onResponse(
                call: Call<PopularMoviesResponce>,
                response: Response<PopularMoviesResponce>
            ) {
                Log.d("Retrofit2", response.body()?.results.toString())
                App.database?.movieDao()?.insertAll(response.body()?.results!!)
            }

            override fun onFailure(call: Call<PopularMoviesResponce>, t: Throwable) {
                Log.d("Retrofit2", "Fail")
            }
        })
    }
}