package com.example.churchwallpaperapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AssemblyInfoScreen(
    onAddScriptureButtonClicked: () -> Unit,
    onAddImageButtonClicked: () -> Unit,
    onSaveWallpaperButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "AssemblyInfoScreen!",
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = onAddScriptureButtonClicked) {
            Text(text = "Add Scripture")
        }
        Button(onClick = onAddImageButtonClicked) {
            Text(text = "Add Image")
        }
        Button(onClick = onSaveWallpaperButtonClicked) {
            Text(text = "Save Wallpaper")
        }
    }
}

@Preview
@Composable
fun AssemblyIntroScreenPreview() {
    AssemblyInfoScreen(
        onAddScriptureButtonClicked = {},
        onAddImageButtonClicked = {},
        onSaveWallpaperButtonClicked = {}
    )
}