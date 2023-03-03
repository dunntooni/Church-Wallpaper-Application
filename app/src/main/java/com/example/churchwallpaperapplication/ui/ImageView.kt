package com.example.churchwallpaperapplication.ui

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.view.View
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import com.example.churchwallpaperapplication.R

class ImageView(ctx: Context, onBitmapCreated: (bitmap: Bitmap) -> Unit) : LinearLayout(ctx) {
    @Composable
    fun CaptureBitmap(
        content: @Composable () -> Unit
    ) : () -> Bitmap {

        val context = LocalContext.current
        // we will use compose view as target to get bitmap
        val composeView = remember {ComposeView(context)}

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
}