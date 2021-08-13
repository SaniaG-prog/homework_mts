package com.mtsteta.homework1.database.dao

import androidx.room.*
import com.mtsteta.homework1.database.entities.Actor

@Dao
interface ActorDao {
    @Query("SELECT * FROM actors")
    fun getAll(): List<Actor>

    @Insert
    fun insertAll(actors: List<Actor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actor: Actor): Long

    @Update
    fun update(actor: Actor)

    @Delete
    fun delete(actor: Actor)

    @Query("DELETE FROM actors WHERE id = :actorId")
    fun deleteById(actorId: Long)

    @Query("DELETE FROM actors")
    fun deleteAll()

    @Query("SELECT * from actors WHERE film_id = :filmId")
    fun getActorsByFilmId(filmId: Long): List<Actor>
}