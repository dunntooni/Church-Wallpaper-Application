package com.example.churchwallpaperapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun displayItem(
    index: Int,
    modifier: Modifier = Modifier,
    listItem: String,
    selected: Boolean,
    onClick: (Int, String) -> Unit,
    prefix: String
) {
    Text(
        text = prefix + listItem,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(if (selected) MaterialTheme.colors.primary else Color.Transparent)
            .padding(20.dp)
            .clickable {
                onClick.invoke(index, listItem)
            },

    )
    Divider(color = Color.Gray, thickness = 1.dp)

}

@Composable
fun ListSelectionScreen(
    modifier: Modifier = Modifier,
    onSaveButtonClicked: () -> Unit,
    onIntOptionSelected: (Int) -> Unit,
    onStringOptionSelected: (String) -> Unit = {},
    listItems: List<String>,
    prefix: String = "",
    isBook: Boolean = false,
) {
    // Some code below was graciously stolen from user msasha on https://stackoverflow.com/questions/66793855/compose-lazycolumn-select-one-item
    var selectedIndex by remember{mutableStateOf(-1)}
    val onItemClick = { index: Int, listItem: String -> selectedIndex = index; if (!isBook) {onIntOptionSelected(index + 1)} else {onStringOptionSelected(listItem)} }

    LazyColumn(verticalArrangement = Arrangement.SpaceEvenly) {
        itemsIndexed(listItems) {
                index, listItem -> displayItem(
            index = index,
            listItem = listItem,
            selected = selectedIndex == index,
            onClick = onItemClick,
            prefix = prefix,)
        }
    }

}