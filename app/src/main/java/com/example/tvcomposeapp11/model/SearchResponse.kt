package com.example.mytvappcompose.model



data class SearchResponse(
    val page: Int,
    val results: List<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)