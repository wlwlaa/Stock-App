package com.example.project1.models.search

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
}
