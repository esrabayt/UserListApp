package com.esrakaya.userlistapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.esrakaya.userlistapp.domain.model.onError
import com.esrakaya.userlistapp.domain.model.onSuccess
import com.esrakaya.userlistapp.domain.usecase.FetchPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchPersonUseCase: FetchPersonUseCase) :
    ViewModel() {
    private val viewStateFlow = MutableStateFlow(MainViewData())
    val viewStateLiveData: LiveData<MainViewData> = viewStateFlow.asLiveData()

    val lockLoadMore: Boolean
        get() = viewStateFlow.value.hasNextPage.not() || viewStateFlow.value.isLoading

    fun getPeople() = viewModelScope.launch {
        viewStateFlow.update { it.copy(isLoading = true) }
        fetchPerson().also {
            viewStateFlow.update { it.copy(isLoading = false) }
        }
    }

    fun onRefresh() = viewModelScope.launch {
        viewStateFlow.update { it.copy(isRefreshing = true, next = null) }
        fetchPerson().also {
            viewStateFlow.update { it.copy(isRefreshing = false) }
        }
    }

    fun onShownErrorMessage() {
        viewStateFlow.update { it.copy(errorMessage = null) }
    }

    private suspend fun fetchPerson() {
        val next = viewStateFlow.value.next
        fetchPersonUseCase(next).onSuccess { (people, next) ->
            val currentItems = viewStateFlow.value.items.orEmpty()
            val newItems = currentItems.toMutableList().apply {
                addAll(people.map(::MainItemViewData))
            }
            viewStateFlow.update { it.copy(items = newItems, next = next) }
        }.onError { message ->
            viewStateFlow.update { it.copy(errorMessage = message) }
        }
    }
}