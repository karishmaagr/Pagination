package com.example.gojekpractice.domain

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gojekpractice.base.BaseViewModel
import com.example.gojekpractice.model.StarWarsPeopleData
import kotlinx.coroutines.flow.Flow

class StarWarsViewModel : BaseViewModel() {

    val repo: StarWarRepo = StarrWarRepoImpl()

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