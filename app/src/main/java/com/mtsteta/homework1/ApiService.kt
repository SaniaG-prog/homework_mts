package com.mtsteta.homework1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL_FOR_POPULAR_MOVIES = "https://api.themoviedb.org/3/"
const val API_KEY = "7257cff7ef7a8d98f7f22351f8c6aa05"
const val LANGUAGE = "ru"

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE): PopularMoviesResponce

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_FOR_POPULAR_MOVIES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}