package com.example.gojekpractice.domain

import android.net.Uri
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gojekpractice.base.BaseViewModel
import com.example.gojekpractice.model.Resource
import com.example.gojekpractice.model.StarWarsPeopleData
import com.example.gojekpractice.model.StarWarsResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.internal.wait

class StarWarsViewModel : BaseViewModel() {

    val repo: StarWarRepo = StarrWarRepoImpl()
    val _pageNumber = MutableLiveData<Int>()


    init {
        getAllCharacters()
    }

    private lateinit var _starWarFlow: Flow<PagingData<StarWarsPeopleData>>
    val starWarFlow: Flow<PagingData<StarWarsPeopleData>>
        get() = _starWarFlow

    private fun getAllCharacters() = launchPagingAsync({
        repo.getStarWarsCharacter().cachedIn(viewModelScope)
    }, {
        _starWarFlow = it
    })
}