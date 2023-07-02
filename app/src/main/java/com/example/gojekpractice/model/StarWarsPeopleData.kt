package com.example.gojekpractice.model

import com.google.gson.annotations.SerializedName

data class StarWarsPeopleData(
    @SerializedName("birth_year") val birth_year: String? = null,

    @SerializedName("created") val created: String? = null,

    @SerializedName("edited") val edited: String? = null,

    @SerializedName("eye_color") val eye_color: String? = null,

    @SerializedName("films") val films: List<String>? = null,

    @SerializedName("gender") val gender: String? = null,

    @SerializedName("hair_color") val hair_color: String? = null,

    @SerializedName("height") val height: String? = null,

    @SerializedName("homeworld") val homeworld: String? = null,

    @SerializedName("mass") val mass: String? = null,

    @SerializedName("name") val name: String? = null,

    @SerializedName("skin_color") val skin_color: String? = null,

    @SerializedName("species") val species: List<String>? = null,

    @SerializedName("starships") val starships: List<String>? = null,

    @SerializedName("url") val url: String? = null,

    @SerializedName("vehicles") val vehicles: List<String>? = null
)