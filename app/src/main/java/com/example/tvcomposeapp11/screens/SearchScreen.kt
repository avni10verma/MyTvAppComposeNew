package com.example.tvcomposeapp11.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import coil.compose.rememberImagePainter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mytvappcompose.model.MovieResult
import com.example.tvcomposeapp11.MovieViewModel



@Composable
fun SearchScreen(navController: NavHostController) {


    val viewModel: MovieViewModel = hiltViewModel()


    val movies by viewModel.movies.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.observeAsState(emptyList())

    // Observe search results and update UI accordingly
    LaunchedEffect(searchResults) {
        // Any additional side effects or operations can be performed here
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .padding(16.dp)) {

        // Search input field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newText ->
                searchQuery = newText
                if (newText.isNotEmpty()) {
                    // Trigger search when text changes
                    viewModel.searchMovies(newText)
                }
            },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Optionally handle the search action when 'Search' is pressed
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display search results in a grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(searchResults) { movie ->
                MoviePosterItem(movie = movie) //{
                    // Handle movie item click here
                    // For example, navigate to a movie detail screen
                   // navController.navigate("movieDetail/${Uri.encode(movies.poster_path)}/${movies.title}/${movies.overview}\") }") {
                        // Optional: configure the navigation options
                        //launchSingleTop = true
                        //restoreState = true
                   // }
                }
            }
        }
    }

@Composable
fun MoviePosterItem(movie: MovieResult) {
    Card(
        modifier = Modifier
            .padding(4.dp),
            //.clickable { onClick(movie) },
        shape = MaterialTheme.shapes.medium
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            AsyncImage( modifier = Modifier
                .aspectRatio(16f/9f),
                model = "${"https://image.tmdb.org/t/p/w500"+movie.poster_path}",
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}
