package com.example.gojekpractice.model

import com.google.gson.annotations.SerializedName

data class StarWarsPeopleData(
    @SerializedName("birth_year") val birth_year: String,
    @SerializedName("created") val created: String,
    @SerializedName("edited") val edited: String,
    @SerializedName("eye_color") val eye_color: String,
    @SerializedName("films") val films: List<String>,
    @SerializedName("gender") val gender: String,
    @SerializedName("hair_color") val hair_color: String,
    @SerializedName("height") val height: String,
    @SerializedName("homeworld") val homeworld: String,
    @SerializedName("mass") val mass: String,
    @SerializedName("name") val name: String,
    @SerializedName("skin_color") val skin_color: String,
    @SerializedName("species") val species: List<String>,
    @SerializedName("starships") val starships: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("vehicles") val vehicles: List<String>
)