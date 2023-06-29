package com.example.gojekpractice.domain

import com.example.gojekpractice.model.StarWarsResponse
import com.example.gojekpractice.networking.RetrofitClientInstance
import retrofit2.Response

class StartWarApi {
    val apiService: StartWarApiService =
        RetrofitClientInstance.getClient().create(StartWarApiService::class.java)

    suspend fun getStarWarsCharacter(pageNumber: Int): Response<StarWarsResponse> {
        return apiService.getStarWarCharacterResponse(pageNumber)
    }
}