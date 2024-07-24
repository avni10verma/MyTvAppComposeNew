package com.example.tvcomposeapp11.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tvcomposeapp11.screens.AppsScreen
import com.example.tvcomposeapp11.screens.DetailScreen
import com.example.tvcomposeapp11.screens.FavouriteScreen
import com.example.tvcomposeapp11.screens.HomeScreen
import com.example.tvcomposeapp11.screens.MoviesScreen
import com.example.tvcomposeapp11.screens.SearchScreen
import com.example.tvcomposeapp11.screens.SettingsScreen
import com.example.tvcomposeapp11.screens.VideoPlayerScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home_screen") {
        composable(route = "home_screen") {
            HomeScreen(navController)
        }
        composable("favourites"){
            FavouriteScreen(navController = navController)
        }
        composable("movies"){
            MoviesScreen(navController = navController)
        }
        composable("apps"){
            AppsScreen(navController = navController)
        }
        composable("search screen") {
            SearchScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }

        composable(
            route = "detail/{posterPath}/{title}/{overview}",
            arguments = listOf(
                navArgument("posterPath") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("overview") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val posterPath = backStackEntry.arguments?.getString("posterPath") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val overview = backStackEntry.arguments?.getString("overview") ?: ""
           DetailScreen(navController, posterPath, title, overview)
        }
        composable("videoPlayer") {
           VideoPlayerScreen(navController)
        }



    }
}
