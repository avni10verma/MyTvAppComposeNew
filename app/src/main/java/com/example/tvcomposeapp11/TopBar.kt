package com.example.tvcomposeapp11.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tvcomposeapp11.R

@Composable
fun TopBar(navController: NavController) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

          Image(
              colorFilter = ColorFilter.tint(Color.White),
              modifier = Modifier
                  .size(28.dp)
                  .align(Alignment.CenterVertically)
                  .focusable()
                  .clickable {  },
              painter = painterResource(id = R.drawable.user),
              contentDescription = ""
          )


            Button(
                modifier = Modifier.focusable(),
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = { navController.navigate("Home screen") }
            )
            {
                Text(text = "Home", color = Color.White)
            }


            Button(
                modifier = Modifier.focusable(),
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = { navController.navigate("favourites") }
            )
            {
                Text(text = "Favorites", color = Color.White)
            }


            Button(
                modifier = Modifier.focusable(),
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = { /* Handle Movies button click */ }
            )
            {
                Text(text = "Movies", color = Color.White)
            }


            Button(
                modifier = Modifier.focusable(),
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = { /* Handle Search button click */ })
            {
                Text(text = "Apps", color = Color.White)
            }

        Image(
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.CenterVertically)
                .focusable()
                .clickable { navController.navigate("search screen") },
            painter = painterResource(id = R.drawable.search22),
            contentDescription = ""
        )
        Image(colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .size(28.dp)
                .focusable()
                .align(Alignment.CenterVertically)
                .clickable {  },
            painter = painterResource(id = R.drawable.setting2),
            contentDescription = ""
        )

    }
}



