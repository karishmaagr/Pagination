package com.example.gojekpractice

import android.net.Uri
import androidx.paging.PagingSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gojekpractice.domain.StarWarDataSource
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class StarWarPagingSourceTest {
    private val starWarDataFactory = StarWarDataFactory()
    val fakeStarWarsResponses = listOf(
        starWarDataFactory.createStarWarData(1),
        starWarDataFactory.createStarWarData(2),
        starWarDataFactory.createStarWarData(3),
    )

    private val fakeApi = FakeStarWarApi().apply {
        fakeStarWarsResponses.forEachIndexed { index, starWarsResponse ->
            addStarWarData(starWarsResponse, index + 1)
        }
    }

    @Test
    fun pagingSourceItemLoadTest() = runTest {
        val pagingSource = StarWarDataSource(fakeApi)
        assertEquals(
            expected = PagingSource.LoadResult.Page(
                data = fakeStarWarsResponses[0].starWarsPeopleData, prevKey = null, nextKey = 2
            ),
            actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null, loadSize = 3, placeholdersEnabled = false
                )
            ),
        )
    }

    @Test
    fun testUri() = runTest {
        val nextUrl: String = "https://swapi.dev/api/people/?page=2"
        val uri = Uri.parse(nextUrl)
        val pageNumber = uri.getQueryParameter("page")
        Assert.assertEquals(2, pageNumber?.toInt())
    }
}