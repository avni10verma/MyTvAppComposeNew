package com.example.tvcomposeapp11

import TopBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController



@Composable
fun ScreenWithTopBar(navController: NavController, content: @Composable () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController)
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}