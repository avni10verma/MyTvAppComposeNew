package com.example.tvcomposeapp11.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.mytvappcompose.model.MovieResult
import com.example.tvcomposeapp11.MovieViewModel
import com.example.tvcomposeapp11.ScreenWithTopBar
import com.example.tvcomposeapp11.interfaces.MovieApiService
import com.example.tvcomposeapp11.interfaces.RetrofitInstance
import com.example.tvcomposeapp11.repository.MovieRepositoryImpl
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    ScreenWithTopBar(navController = navController) {

        val viewModel: MovieViewModel = hiltViewModel()
        val homeFocusRequester = remember { FocusRequester() }

        val movies by viewModel.movies.observeAsState(emptyList())



        val topRatedMovies = movies.getOrNull(1)?.take(5) ?: emptyList()
        val otherCategories = movies.filterIndexed { index, _ -> index != 1 }

        val pagerState = rememberPagerState {
            topRatedMovies.size
        }

        LaunchedEffect(Unit) {
            // Example: Scroll to the first page
            pagerState.scrollToPage(0)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            item {
                HorizontalPager(modifier = Modifier
                    .padding(10.dp)
                    .focusRequester(homeFocusRequester),
                    state = pagerState) { page ->
                    TopRatedMoviePage(movie = topRatedMovies[page],navController,viewModel,homeFocusRequester)

                }
            }
            items(otherCategories.size) { index ->
                Log.d("HomeScreen", "Category $index size: ${otherCategories[index].size}")
                MovieCategorySection(otherCategories[index], index = index,navController)
            }
        }



    }
}

@Composable
fun TopRatedMoviePage(movie : MovieResult,navController: NavHostController,viewModel: MovieViewModel,homeFocusRequester: FocusRequester) {

    val isFocused = remember { mutableStateOf(false)}


    val lazyRowFocusRequester = remember { FocusRequester() }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primary)

    )
    {
        Image(
            painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500"+ movie.poster_path),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            Column {
                Text(
                    color = Color.White,
                    modifier = Modifier
                    .padding(8.dp),

                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium)


                     Button(
                           modifier = Modifier.size(width = 100.dp, height = 35.dp)
                               .onFocusChanged { focusState ->
                               isFocused.value = focusState.isFocused }
                               .focusable()
                               .focusRequester(lazyRowFocusRequester)
                               .border(
                                   width = if (isFocused.value) 2.dp else 0.dp,
                                   color = if (isFocused.value) Color.Blue else Color.Transparent
                               )


                               .onKeyEvent {
                                       if (it.nativeKeyEvent.keyCode == android.view.KeyEvent.KEYCODE_DPAD_DOWN && it.nativeKeyEvent.action == android.view.KeyEvent.ACTION_DOWN) {
                                           lazyRowFocusRequester.requestFocus()
                                           true
                                       }

                                       else  false


                               },

                           colors = ButtonDefaults.buttonColors(Color.LightGray.copy(alpha = 0.5f)),
                           shape = RoundedCornerShape(8.dp),
                           onClick = { Log.d("TopRatedMoviePage", "Navigating to detail: ${movie.poster_path}, ${movie.title}, ${movie.overview}")
                               navController.navigate("detail/${Uri.encode(movie.poster_path)}/${movie.title}/${movie.overview}") }


                       ) {
                           Text(text = "Watch Now",color = Color.White,
                               fontSize = 10.sp)

                       }


                   }

                }

            }



        }








@Composable
fun MovieCategorySection(categoryMovies:List<MovieResult>, index: Int,navController: NavHostController){

    Log.d("MovieCategory","${categoryMovies.size}")
    val categoryTitle = when (index) {
        0 -> "Popular Movies"
        1 -> "Trending Movies"
        2 -> "Now Playing Movies"
        3 -> "Upcoming Movies"
        else -> "Category"
    }
    Column(){
        Text(
            text = categoryTitle,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(8.dp)


        )

        val lazyRowFocusRequester = remember { FocusRequester() }
        LazyRow(modifier = Modifier
            .padding(15.dp)
            .focusRequester(lazyRowFocusRequester)
            .fillMaxWidth()){
            items(categoryMovies){ movie->
                MovieItem(movie = movie, onClick = { navController.navigate("detail/${Uri.encode(movie.poster_path)}/${movie.title}/${movie.overview}") })
            }
        }


    }
}

@Composable
fun MovieItem(movie: MovieResult,onClick: () -> Unit){
    Log.d("MovieItem","${"https://image.tmdb.org/t/p/w500"+movie.poster_path}")
    val isFocused = remember { mutableStateOf(false)}
    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(100.dp)
            .onFocusChanged { focusState ->
                isFocused.value = focusState.isFocused
            }
            .focusable()
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .border(
                width = if (isFocused.value) 2.dp else 0.dp,
                color = if (isFocused.value) Color.Blue else Color.Transparent
            )

    )
     {
        AsyncImage( modifier = Modifier
            .graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            }
            .aspectRatio(16f / 9f),
            model = "${"https://image.tmdb.org/t/p/w500"+movie.poster_path}",
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

