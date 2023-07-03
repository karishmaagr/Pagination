package com.example.gojekpractice

import com.example.gojekpractice.model.StarWarsPeopleData
import com.example.gojekpractice.model.StarWarsResponse

class StarWarDataFactory {

    fun createStarWarData( pageNumber: Int): StarWarsResponse {
        val post = StarWarsResponse(
            count = 20,
            next = "https://swapi.dev/api/people/?page=${pageNumber+1}",
            previous = "null",
            starWarsPeopleData = getStarWarDataList(pageNumber)
        )
        return post
    }

    fun getStarWarDataList(pageNumber: Int): List<StarWarsPeopleData> {
        return listOf(
            StarWarsPeopleData(
                name = "Will smith $pageNumber", url = "https://swapi.dev/api/people/1/"
            ), StarWarsPeopleData(
                name = "Sam smith $pageNumber", url = "https://swapi.dev/api/people/2/"
            ), StarWarsPeopleData(
                name = "Cal smith $pageNumber", url = "https://swapi.dev/api/people/3/"
            )
        )
    }
}