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
    var posterPath: String,

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @SerializedName("genre_ids")
    @Ignore
    var genreIds: List<Int>,

    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Long,

    @SerializedName("original_title")
    @Ignore
    var originalTitle: String,

    @SerializedName("original_language")
    @Ignore
    var originalLanguage: String,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String,

    @SerializedName("backdrop_path")
    @Ignore
    var backdropPath: String,

    @SerializedName("popularity")
    @Ignore
    var popularity: Float,

    @SerializedName("vote_count")
    @Ignore
    var voteCount: Int,

    @SerializedName("video")
    @Ignore
    var video: Boolean,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Float
) {
    constructor(posterPath: String, adult: Boolean, overview: String, releaseDate: String,
                id: Long, title: String, voteAverage: Float) : this(posterPath, adult, overview,
        releaseDate, emptyList(), id, "", "", title, "",
        0.0f, 0, false, voteAverage)
}
