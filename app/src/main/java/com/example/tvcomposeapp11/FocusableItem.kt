package com.example.tvcomposeapp11

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun FocusableItem(
    focusRequester: FocusRequester,
    borderColor: Color = Color.Red,
    borderWidth: Dp = 2.dp,
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .focusRequester(focusRequester)
            .focusable()
            .border(
                width = if (isFocused) borderWidth else 0.dp,
                color = if (isFocused) borderColor else Color.Transparent
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
    ) {
        content(Modifier)
    }
}