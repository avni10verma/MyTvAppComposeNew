package com.example.tvcomposeapp11

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(
    currentPage: Int,
    pageCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        repeat(pageCount) { page ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (page == currentPage) 12.dp else 8.dp)
                    .background(if (page == currentPage) Color.White else Color.Gray, shape = RoundedCornerShape(50))
            )
        }
    }
}
