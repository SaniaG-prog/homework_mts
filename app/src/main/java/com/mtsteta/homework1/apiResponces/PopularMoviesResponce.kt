package com.mtsteta.homework1.apiResponces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mtsteta.homework1.database.entities.Movie

data class PopularMoviesResponce(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val results: List<Movie>,

    @SerializedName("total_results")
    @Expose
    val countOfResults: Int,

    @SerializedName("total_pages")
    @Expose
    val countOfPages: Int
)
