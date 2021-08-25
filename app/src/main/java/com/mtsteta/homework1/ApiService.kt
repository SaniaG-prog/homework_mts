package com.mtsteta.homework1

import com.mtsteta.homework1.apiResponces.ActorsResponce
import com.mtsteta.homework1.apiResponces.GenresResponce
import com.mtsteta.homework1.apiResponces.PopularMoviesResponce
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "7257cff7ef7a8d98f7f22351f8c6aa05"
const val LANGUAGE = "ru"

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE): PopularMoviesResponce

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE): GenresResponce

    @GET("movie/{movie_id}/credits")
    suspend fun getActors(
        @Path("movie_id") movieId: Long,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE): ActorsResponce

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}

fun Retrofit.Builder.setClient() = apply {
    val okHttpClient = OkHttpClient.Builder()
        .addHttpLoggingInterceptor()
        .build()

    this.client(okHttpClient)
}

private fun OkHttpClient.Builder.addHttpLoggingInterceptor() = apply {
    if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        this.addNetworkInterceptor(interceptor)
    }
}
