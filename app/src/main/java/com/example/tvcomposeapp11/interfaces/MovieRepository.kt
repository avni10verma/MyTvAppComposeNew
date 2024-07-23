package com.example.tvcomposeapp11.interfaces

import androidx.lifecycle.LiveData
import com.example.mytvappcompose.model.MovieResult

interface MovieRepository {
    val movies: LiveData<List<List<MovieResult>>>
    suspend fun fetchAllMovies()

   suspend fun searchMovies(query: String): List<MovieResult>



}

