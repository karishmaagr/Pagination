package com.example.gojekpractice.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gojekpractice.model.StarWarsPeopleData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarWarsViewModel @Inject constructor(private val repo: StarWarRepo) : ViewModel() {

    init {
        getAllCharacters()
    }

    private lateinit var _starWarFlow: Flow<PagingData<StarWarsPeopleData>>
    val starWarFlow: Flow<PagingData<StarWarsPeopleData>>
        get() = _starWarFlow

    private fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val result = repo.getStarWarsCharacter().cachedIn(viewModelScope)
                _starWarFlow = result
            } catch (ex: Exception) {
                //handle error case
            }
        }
    }
}