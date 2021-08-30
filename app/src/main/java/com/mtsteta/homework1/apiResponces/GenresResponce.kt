package com.mtsteta.homework1.apiResponces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mtsteta.homework1.database.entities.Genre

data class GenresResponce(
    @SerializedName("genres")
    @Expose
    val genres: List<Genre>
)
