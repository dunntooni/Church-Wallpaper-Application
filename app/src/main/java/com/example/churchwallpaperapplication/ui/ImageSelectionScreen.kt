package com.example.churchwallpaperapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImageSelectionScreen(
    modifier: Modifier = Modifier,
    onSaveButtonClicked: () -> Unit
) {
    Column(Modifier.padding(16.dp)) {
        Text(
            text = "ImageSelectionScreen!",
            fontSize = 30.sp
            )
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = onSaveButtonClicked) {
            Text(text = "Save Changes")
        }
    }
}