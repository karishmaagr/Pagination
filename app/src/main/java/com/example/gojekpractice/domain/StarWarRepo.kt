package com.example.gojekpractice.domain

import androidx.paging.PagingData
import com.example.gojekpractice.model.StarWarsPeopleData
import kotlinx.coroutines.flow.Flow

interface StarWarRepo {
    suspend fun getStarWarsCharacter(): Flow<PagingData<StarWarsPeopleData>>
}