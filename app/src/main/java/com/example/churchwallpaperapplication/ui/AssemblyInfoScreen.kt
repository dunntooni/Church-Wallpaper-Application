package com.example.churchwallpaperapplication.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

@Composable
fun CaptureBitmap(
    content: @Composable () -> Unit
) : () -> Bitmap {

    val context = LocalContext.current
    // we will use compose view as target to get bitmap
    val composeView = remember { ComposeView(context) }

    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    // put compose native view to compose ui using AndroidView
    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke();
                }
            }
        }
    )
    return ::captureBitmap
}

// This function displays the image and given scripture. Genesis 1:1 is given by default.
// It is scanned into a bitmap by the function above.
@Composable
fun displayWallpaper(selectedImageName: String, verse_text: String, dimensions: List<Int>) {
    var context = LocalContext.current;
    var view = LocalView.current;
    if (selectedImageName != "") {
        Box(modifier =
            Modifier.height((dimensions[0] * 0.6).dp)
                .width((dimensions[1] * 0.6).dp)
                .border(width = 1.dp, shape = RectangleShape, color = Color.DarkGray)) {
            val imageResourceValue = selectedImageName.split(".")[0]
            val drawableId = remember(imageResourceValue) {
                context.resources.getIdentifier(
                    imageResourceValue,
                    "drawable",
                    context.packageName
                )
            }
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = "Selected Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(text = verse_text, modifier = Modifier.align(Alignment.BottomCenter))
        }
    } else {
        Box(modifier =
        Modifier.height((dimensions[0] * 0.6).dp)
            .width((dimensions[1] * 0.6).dp)
            .border(width = 1.dp, shape = RectangleShape, color = Color.DarkGray)) {
            Text(text = "Image will appear here", modifier = Modifier.align(Alignment.Center))
            Text(text = verse_text, modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun AssemblyInfoScreen(
    onAddScriptureButtonClicked: () -> Unit,
    onAddImageButtonClicked: () -> Unit,
    onSaveWallpaperButtonClicked: (Bitmap?) -> Unit,
    volume: Int, // Leaving these parameters in for now if I decide to show a scripture reference
    book: Int,
    chapter: Int,
    verse_id: Int,
    verse_text: String,
    selectedImageName: String,
    dimensions: List<Int>,
    modifier: Modifier = Modifier
) {
//    val onSaveWallpaperButtonClick = {
//        imgBitmap: Bitmap? -> onSaveWallpaperButtonClicked(imgBitmap)
//    }
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

        Spacer(modifier = Modifier.height(16.dp))
        val imgBitmap = CaptureBitmap {
            displayWallpaper(
                selectedImageName = selectedImageName,
                verse_text = verse_text,
                dimensions = dimensions
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = { onSaveWallpaperButtonClicked(imgBitmap.invoke()) }) {
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
        onSaveWallpaperButtonClicked = {},
        0, 0, 0, 0,
        "peepeepoopoo",
        "image.jpg",
        listOf(0,0)
    )
}