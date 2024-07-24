package com.example.tvcomposeapp11.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tvcomposeapp11.MovieViewModel


@Composable
fun  FavouriteScreen(navController: NavController){
    val viewModel: MovieViewModel = hiltViewModel()

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