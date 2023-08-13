package com.example.project1.viewmodels.search

sealed class SearchEvent {
    object Refresh: SearchEvent()
    data class OnSearchQueryChange(val query: String): SearchEvent()
}
