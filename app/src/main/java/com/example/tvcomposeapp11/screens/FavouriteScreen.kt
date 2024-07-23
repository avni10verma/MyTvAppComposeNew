package com.example.tvcomposeapp11.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tvcomposeapp11.MovieViewModel
import com.example.tvcomposeapp11.MovieViewModelFactory
import com.example.tvcomposeapp11.interfaces.MovieApiService
import com.example.tvcomposeapp11.repository.MovieRepositoryImpl

@Composable
fun  FavouriteScreen(navController: NavController){
    val repository = MovieRepositoryImpl(MovieApiService.create())
    val viewModel: MovieViewModel = viewModel(
        factory = MovieViewModelFactory(repository)
    )

    val favourites by viewModel.favourites.observeAsState(emptyList())


    LazyColumn {
        items(favourites){ favourite->
            com.example.tvcomposeapp11.presentation.components.MovieItem(
                movie = favourite,
                onFavoriteToggle = {viewModel.removeFavourite(favourite) },
                isFavorite = true
            )
        }
    }


}