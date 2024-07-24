package com.example.tvcomposeapp11.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tvcomposeapp11.MovieViewModel


@Composable
fun MoviesScreen(navController: NavHostController) {


    val viewModel: MovieViewModel = hiltViewModel()
    val movies by viewModel.movies.observeAsState(emptyList())

    val otherCategories = movies.filterIndexed { index, _ -> index != 1 }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        items(otherCategories.size) { index ->

            MovieCategorySection(otherCategories[index], index = index, navController = navController)
        }
    }
}