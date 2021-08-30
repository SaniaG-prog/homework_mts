package com.mtsteta.homework1.database.dao

import androidx.room.*
import com.mtsteta.homework1.database.entities.Genre

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    fun getAll(): List<Genre>

    @Query("SELECT * FROM genres WHERE is_interesting = :isInteresting")
    fun getByInterest(isInteresting: Boolean): List<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(genres: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: Genre): Long

    @Update
    fun update(genre: Genre)

    @Delete
    fun delete(genre: Genre)

    @Query("DELETE FROM genres WHERE id = :genreId")
    fun deleteById(genreId: Int)

    @Query("DELETE FROM genres")
    fun deleteAll()
}