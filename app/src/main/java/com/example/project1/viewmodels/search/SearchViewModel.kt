package com.example.project1.viewmodels.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.api_client.MyApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {
    var state by mutableStateOf(SearchState())

    private var _searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Refresh -> performSearch()
            is SearchEvent.OnSearchQueryChange -> {
                state = state.copy(query = event.query)
                _searchJob?.cancel()
                _searchJob = viewModelScope.launch {
                    delay(800L)
                    performSearch()
                }
            }
        }
    }

    private fun performSearch(query: String = state.query) {
        if (query.isBlank()) {
            state = state.copy(searchResults = emptyList(), searchCount = 0)
            return
        }

        _searchJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = MyApiClient.finnhubService.getSymbolLookup(query)

                //if (response.body() == "")

                withContext(Dispatchers.Main) {
                    state = if (response.isSuccessful) {
                        state.copy(
                            searchResults = response.body()?.result ?: emptyList(),
                            searchCount = response.body()?.count ?: 0,
                            isSearching = false
                        )
                    } else {
                        state.copy(
                            errorMessage = "Ошибка: ${response.message()}",
                            isSearching = false
                        )
                    }
                }
            } catch (e: Exception) {
                state = state.copy(
                    errorMessage = "Ошибка: ${e.message}",
                    isSearching = false
                )
            }
        }
    }
}
