package com.example.mytvappcompose.model


data class MovieResponse(
    val page: Int,
    val results: List<MovieResult>
)