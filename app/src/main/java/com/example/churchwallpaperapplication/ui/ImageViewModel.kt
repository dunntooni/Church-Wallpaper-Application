package com.example.churchwallpaperapplication.ui

import android.util.JsonReader
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.churchwallpaperapplication.data.ImageUiState
import com.example.churchwallpaperapplication.data.ScriptureDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.IOException
import java.io.InputStream
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ImageViewModel : ViewModel() {
    /**
     * Cupcake state for this order
     */
    private val _uiState = MutableStateFlow(ImageUiState())
    val uiState: StateFlow<ImageUiState> = _uiState.asStateFlow()

    fun setVolumeId_temp(volumeId: Int){
        _uiState.update { currentState ->
            currentState.copy(
                volumeId_temp = volumeId
            )
        }
    }
    fun setBookId_temp(bookId: Int){
        _uiState.update { currentState ->
            currentState.copy(
                bookId_temp = bookId
            )
        }
    }
    fun setChapterId_temp(chapterId: Int){
        _uiState.update { currentState ->
            currentState.copy(
                chapterId_temp = chapterId
            )
        }
    }
    fun setVerseId_temp(verseId: Int){
        _uiState.update { currentState ->
            currentState.copy(
                verseId_temp = verseId
            )
        }
    }

    fun overwriteScripture(volumeId_temp: Int, bookId_temp: Int, chapterId_temp: Int, verseId_temp: Int){
        _uiState.update { currentState ->
            currentState.copy(
                volumeId = volumeId_temp,
                bookId = bookId_temp,
                chapterId = chapterId_temp,
                verseId = verseId_temp
            )
        }
    }

    fun setSelectedImage(selectedImage: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedImage = selectedImage
            )
        }
    }
}