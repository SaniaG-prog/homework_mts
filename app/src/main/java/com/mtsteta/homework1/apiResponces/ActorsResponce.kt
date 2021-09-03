package com.mtsteta.homework1.apiResponces

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mtsteta.homework1.database.entities.Actor
import com.mtsteta.homework1.database.entities.PersonFromCrew

data class ActorsResponce(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("cast")
    @Expose
    val cast: List<Actor>,

    @SerializedName("crew")
    @Expose
    val crew: List<PersonFromCrew>
)
