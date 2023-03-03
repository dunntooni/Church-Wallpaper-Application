package com.example.churchwallpaperapplication.data

data class ImageUiState(
    /** Selected cupcake quantity (1, 6, 12) */
    val volumeId: Int = -1,
    val bookId: Int = -1,
    val chapterId: Int = -1,
    val verseId: Int = -1,

    // We don't change the actual scripture reference until a user has selected a new
    val volumeId_temp: Int = -1,
    val bookId_temp: Int = -1,
    val chapterId_temp: Int = -1,
    val verseId_temp: Int = -1,

    val bookName: String = "",
    val chapter: Int = -1,
    val verse: Int = -1,


    val selectedImage: String = "",

) {
}
