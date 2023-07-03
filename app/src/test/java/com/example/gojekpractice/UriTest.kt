package com.example.gojekpractice

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UriTest {

    @Test
    fun testUri() {
        val nextUrl: String = "https://swapi.dev/api/people/?page=2"
        val uri = Uri.parse(nextUrl)
        val pageNumber: String? = uri.getQueryParameter("page")
        Assert.assertEquals(2, pageNumber?.toInt())
    }
}