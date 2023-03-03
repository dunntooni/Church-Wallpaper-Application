package com.example.churchwallpaperapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun displayImageItem(
    modifier: Modifier = Modifier,
    listItem: String,
    selected: Boolean,
    onClick: (String) -> Unit,
) {
    Text(
        text = listItem,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(if (selected) MaterialTheme.colors.primary else Color.Transparent)
            .padding(20.dp)
            .clickable {
                onClick.invoke(listItem)
            }
    )
    Divider(color = Color.Gray, thickness = 1.dp)
}


@Composable
fun ImageSelectionScreen(
    modifier: Modifier = Modifier,
    onImageSelected: (String) -> Unit,
    listItems: List<String>
) {
    var selectedIndex by remember{mutableStateOf(-1)};
    val onItemClick = {listItem: String -> onImageSelected(listItem)}
    
    LazyColumn(verticalArrangement = Arrangement.SpaceEvenly) {
        itemsIndexed(listItems) {
            index, listItem -> displayImageItem(
            listItem = listItem,
            selected = selectedIndex == index,
            onClick = onItemClick
        )
        }
    }
}