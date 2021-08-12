package com.mtsteta.homework1.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "rate_score")
    val rateScore: Int,

    @ColumnInfo(name = "age_restriction")
    val ageRestriction: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
) {
    constructor(title: String, description: String, rateScore: Int,
                ageRestriction: Int, imageUrl: String) :
            this(null, title, description, rateScore, ageRestriction, imageUrl)
}
