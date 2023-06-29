package com.example.gojekpractice.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gojekpractice.model.StarWarsPeopleData
import kotlinx.coroutines.flow.Flow

class StarrWarRepoImpl() : StarWarRepo {
    val startWarApi: StartWarApi = StartWarApi()

    override suspend fun getStarWarsCharacter(): Flow<PagingData<StarWarsPeopleData>> =
        Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = { StarWarDataSource(startWarApi) }
        ).flow
}
