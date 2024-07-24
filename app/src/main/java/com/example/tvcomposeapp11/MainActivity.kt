package com.example.tvcomposeapp11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.tvcomposeapp11.navigation.NavGraph
import com.example.tvcomposeapp11.ui.theme.TVComposeApp11Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TVComposeApp11Theme {
                AppContent()

            }
        }
    }
}


@Composable
fun AppContent() {
    val navController = rememberNavController()
    NavGraph(navController = navController)

}