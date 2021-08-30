package com.mtsteta.homework1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mtsteta.homework1.database.dao.GenreDao
import com.mtsteta.homework1.database.dao.MovieDao
import com.mtsteta.homework1.database.entities.Actor
import com.mtsteta.homework1.database.entities.Genre
import com.mtsteta.homework1.database.entities.Movie

@Database(entities = [Movie::class, Genre::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao

    companion object {
        private const val DATABASE_NAME = "Movies.db"

        private var instance: AppDatabase? = null

        fun initDatabase(context: Context) {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().
                    fallbackToDestructiveMigration().build()
                }
            }
        }

        fun getInstance(): AppDatabase? {
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}