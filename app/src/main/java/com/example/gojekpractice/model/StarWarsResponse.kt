package com.example.gojekpractice.model

import com.google.gson.annotations.SerializedName

data class StarWarsResponse(
  @SerializedName("count")  val count: Int,
  @SerializedName("next") val next: String,
  @SerializedName("previous") val previous: String,
  @SerializedName("results") val starWarsPeopleData: List<StarWarsPeopleData>
)