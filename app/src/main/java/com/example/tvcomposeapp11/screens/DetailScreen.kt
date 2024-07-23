package com.example.tvcomposeapp11.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

@Composable
fun DetailScreen(navController: NavHostController, posterPath: String, title: String, overview: String) {

    Log.d("Detailscreen","PosterPath: $posterPath, Title: $title, Overview: $overview")
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500$posterPath"),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(16.dp)
        ) {
            Text(text = title, color = Color.White, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = overview, color = Color.White, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
            Button(
                colors = ButtonDefaults.buttonColors(Color.LightGray.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(8.dp),
                onClick = { navController.navigate("videoPlayer") },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Watch Now", color = Color.White)
            }
        }
    }
}