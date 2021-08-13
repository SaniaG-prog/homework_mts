package com.mtsteta.homework1.database.dao

import androidx.room.*
import com.mtsteta.homework1.database.entities.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie): Long

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM movies WHERE id = :movieId")
    fun deleteById(movieId: Long)

    @Query("DELETE FROM movies")
    fun deleteAll()
}