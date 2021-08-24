package com.mtsteta.homework1.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @SerializedName("genre_ids")
    @Ignore
    val genreIds: List<Int>,

    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Long,

    @SerializedName("original_title")
    @Ignore
    val originalTitle: String,

    @SerializedName("original_language")
    @Ignore
    val originalLanguage: String,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("backdrop_path")
    @Ignore
    val backdropPath: String,

    @SerializedName("popularity")
    @Ignore
    val popularity: Float,

    @SerializedName("vote_count")
    @Ignore
    val voteCount: Int,

    @SerializedName("video")
    @Ignore
    val video: Boolean,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Float
) {
    constructor(posterPath: String, adult: Boolean, overview: String, releaseDate: String,
                id: Long, title: String, voteAverage: Float):
            this(posterPath, adult, overview, releaseDate, emptyList(), id, "",
                "", title, "", 0.0f, 0, false, voteAverage)
}
