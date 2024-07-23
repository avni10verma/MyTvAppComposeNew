package com.example.tvcomposeapp11

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytvappcompose.model.MovieResult
import com.example.tvcomposeapp11.interfaces.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movies: LiveData<List<List<MovieResult>>> = repository.movies

    private val _searchResults = MutableLiveData<List<MovieResult>>()
    val searchResults: LiveData<List<MovieResult>> get() = _searchResults


    private val _favourites = MutableLiveData<List<MovieResult>>()
    val favourites : LiveData<List<MovieResult>> get() = _favourites


    init {
        fetchAllMovies()
    }

    fun fetchAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAllMovies()
        }
    }

    fun searchMovies(query: String) {
        Log.d("Search", "Immediate search query: $query")

        viewModelScope.launch(Dispatchers.IO) {
            _searchResults.postValue(repository.searchMovies(query))
        }

    }

    fun addFavourite(movie:MovieResult){
        val updatedFavourites = _favourites.value.orEmpty().toMutableList().apply {
            if(!contains(movie)) add(movie)
        }
        _favourites.value = updatedFavourites
    }

    fun removeFavourite(movie: MovieResult){
        val updatedFavourites = _favourites.value.orEmpty().toMutableList().apply {
            remove(movie)
        }
        _favourites.value = updatedFavourites
    }


}