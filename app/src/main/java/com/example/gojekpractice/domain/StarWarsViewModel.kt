package com.example.gojekpractice.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gojekpractice.model.StarWarsPeopleData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarWarsViewModel @Inject constructor(private val repo: StarWarRepo) : ViewModel() {

    init {
        getAllCharacters()
    }

    private var _starWarFlow: MutableStateFlow<PagingData<StarWarsPeopleData>> = MutableStateFlow(PagingData.empty())
    val starWarFlow: Flow<PagingData<StarWarsPeopleData>>
        get() = _starWarFlow

    private fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val result = repo.getStarWarsCharacter().cachedIn(viewModelScope)
                result.collectLatest {
                    _starWarFlow.emit(it)
                }
            } catch (ex: Exception) {
                //handle error case
            }
        }
    }
}