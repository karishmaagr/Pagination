package com.example.gojekpractice.domain

import com.example.gojekpractice.model.StarWarsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarApiService {
    @GET("people/")
    suspend fun getStarWarCharacterResponse(@Query("page") pageNumber: Int): Response<StarWarsResponse>

}