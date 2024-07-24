package com.example.tvcomposeapp11.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import com.example.mytvappcompose.model.MovieResult
import com.example.tvcomposeapp11.interfaces.MovieApiService
import com.example.tvcomposeapp11.interfaces.MovieRepository
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(private val tmdbService: MovieApiService) : MovieRepository {

    private val _movies = MutableLiveData<List<List<MovieResult>>>()
    override val movies: LiveData<List<List<MovieResult>>> get() = _movies



    override suspend fun fetchAllMovies() {
        val movieResults = coroutineScope {
            movieFetchers.map { fetcher ->
                async { fetcher() }
            }.map { it.await() }
        }
        _movies.postValue(movieResults)


    }


    private suspend fun fetchTrendingMovies(): List<MovieResult> {
        return tmdbService.getTrendingMovies().results
    }

    private suspend fun fetchTopRatedMovies(): List<MovieResult> {
        return tmdbService.getTopRatedMovies().results
    }

    private suspend fun fetchPopularMovies(): List<MovieResult> {
        return tmdbService.getPopularMovies().results
    }

    private suspend fun fetchNowPlayingMovies(): List<MovieResult> {
        return tmdbService.getNowPlayingMovies().results
    }

    private suspend fun fetchUpcomingMovies(): List<MovieResult> {
        return tmdbService.getUpcomingMovies().results
    }


    override suspend fun searchMovies(query: String): List<MovieResult> {
        val searchResult = tmdbService.searchMovies(query).results
        Log.d("serachcheck", "{$searchResult}")
        return  searchResult
    }




    private val movieFetchers = listOf(
        ::fetchTrendingMovies,
        ::fetchTopRatedMovies,
        ::fetchPopularMovies,
        ::fetchNowPlayingMovies,
        ::fetchUpcomingMovies
    )
}


// {
// [[], [], []]
//
// }
