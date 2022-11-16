package com.example.churchwallpaperapplication.ui

import androidx.lifecycle.ViewModel
import com.example.churchwallpaperapplication.data.ImageUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ImageViewModel : ViewModel() {
    /**
     * Cupcake state for this order
     */
    private val _uiState = MutableStateFlow(ImageUiState()) // TODO: Figure out how to get rid of pickupOptions()
    val uiState: StateFlow<ImageUiState> = _uiState.asStateFlow()

    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // add current date and the following 3 dates.
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}