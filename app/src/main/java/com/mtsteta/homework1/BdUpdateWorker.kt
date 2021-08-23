package com.mtsteta.homework1

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mtsteta.homework1.database.entities.Movie
import kotlinx.coroutines.*
import java.lang.Exception

class BdUpdateWorker (appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        try {
            runBlocking {
                coroutineScope {
                    val popularMovies: List<Movie> = withContext(Dispatchers.IO) {
                        App.instance.apiService.getPopularMovies().results
                    }
                    App.database?.movieDao()?.insertAll(popularMovies)
                }
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}
