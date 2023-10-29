package com.example.project1.models.search

import com.example.project1.data.remote.Lookup

data class SearchState(
    var searchResults: List<Lookup> = emptyList(),
    var searchCount: Int = 0,
    var isSearching: Boolean = false,
    val isRefreshing: Boolean = false,
    var query: String = "",
    var errorMessage: String? = null
)