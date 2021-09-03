package com.mtsteta.homework1.database.entities

import com.google.gson.annotations.SerializedName

data class PersonFromCrew(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("gender")
    val gender: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("known_for_department")
    val knownForDepartment: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("popularity")
    val popularity: Number,

    @SerializedName("profile_path")
    val profilePath: String,

    @SerializedName("credit_id")
    val creditId: String,

    @SerializedName("department")
    val department: String,

    @SerializedName("job")
    val job: String
)
