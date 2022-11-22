package com.example.churchwallpaperapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    onBeginButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text(text = "Hello!")
        Text(text = "Welcome to the church wallpaper application.\n" +
                "Press the button below to get started.")
        Button(onClick = onBeginButtonClicked) {
            Text(text = "Begin")
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onBeginButtonClicked = { /*TODO*/ })
}