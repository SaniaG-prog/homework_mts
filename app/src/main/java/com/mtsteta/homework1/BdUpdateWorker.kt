package com.mtsteta.homework1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mtsteta.homework1.database.entities.Genre
import com.mtsteta.homework1.database.entities.Movie
import kotlinx.coroutines.*
import java.lang.Exception

class BdUpdateWorker (appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        try {
            runBlocking {
                coroutineScope {
                    val popularMovies: List<Movie> =
                        App.instance.apiService.getPopularMovies().results
                    val genres: List<Genre> = App.instance.apiService.getGenres().genres
                    addDataToDB(popularMovies, genres)
                }
            }
            return Result.success()
        } catch (e: Exception) {
            Log.d("Worker", "Fail")
            return Result.failure()
        }
    }

    suspend fun addDataToDB(movies: List<Movie>, genres: List<Genre>) {
        App.database?.movieDao()?.insertAll(movies)
        App.database?.genreDao()?.insertAll(genres)
    }
}
