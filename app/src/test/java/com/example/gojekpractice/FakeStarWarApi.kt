package com.example.gojekpractice

import com.example.gojekpractice.domain.StartWarApiService
import com.example.gojekpractice.model.StarWarsResponse
import retrofit2.Response
import java.io.IOException

/**
 * implements the RedditApi with controllable requests
 */
class FakeStarWarApi : StartWarApiService {
    // subreddits keyed by name
    private val model = mutableMapOf<Int, StarWarsResponse>()

    var failureMsg: String? = null

    fun addStarWarData(starWarsResponse: StarWarsResponse ,pageNumber: Int) {
        model[pageNumber] = starWarsResponse
    }

    private fun findStarWarResponse(pageNumber: Int) = model.getOrDefault(
        pageNumber, StarWarsResponse(
            starWarsPeopleData = listOf(), next = "null", previous = "null", count = 0
        )
    )

    override suspend fun getStarWarCharacterResponse(pageNumber: Int): Response<StarWarsResponse> {
        failureMsg?.let {
            throw IOException(it)
        }
        val items = findStarWarResponse(pageNumber)
        println("getStarWarCharacterResponse next is :: " + items.next)
        return Response.success(items)
    }
}