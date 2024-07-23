package com.example.tvcomposeapp11.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController

@Composable
fun VideoPlayerScreen(navController: NavHostController) {
    val context = LocalContext.current

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = ExoPlayer.Builder(context).build().apply {
                    val mediaItem = MediaItem.fromUri("https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8")
                    setMediaItem(mediaItem)
                    prepare()
                    playWhenReady = true
                }

            }
        },
        modifier = Modifier.fillMaxSize()

    )
}
