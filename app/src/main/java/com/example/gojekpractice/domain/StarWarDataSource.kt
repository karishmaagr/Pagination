package com.example.gojekpractice.domain

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gojekpractice.model.StarWarsPeopleData

class StarWarDataSource(private val service: StartWarApi) :
    PagingSource<Int, StarWarsPeopleData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarWarsPeopleData> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getStarWarsCharacter(pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.starWarsPeopleData

            var nextPageNumber: Int? = null
            if (pagedResponse?.next != null) {
                val uri = Uri.parse(pagedResponse.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(), prevKey = null, nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StarWarsPeopleData>): Int = 1
}