package com.example.churchwallpaperapplication.data.entities

import androidx.room.DatabaseView

@DatabaseView(
    "SELECT volumes.id AS volume_id, books.id AS book_id, " +
        "chapters.id AS chapter_id, verses.id AS verse_id, volume_title, book_title, volume_long_title, " +
        "book_long_title, volume_subtitle, book_subtitle, volume_short_title, book_short_title, " +
        "volume_lds_url, book_lds_url, chapter_number, verse_number, scripture_text, book_title " +
        "|| ' ' || chapter_number || ':' || verse_number AS verse_title, book_short_title || ' ' || " +
        "chapter_number || ':' || verse_number AS verse_short_title FROM volumes INNER JOIN books ON " +
        "books.volume_id = volumes.id INNER JOIN chapters ON chapters.book_id = books.id INNER JOIN " +
        "verses ON verses.chapter_id = chapters.id ORDER BY volumes.id, books.id, chapters.id, verses.id",
viewName = "scriptures")
data class Scriptures(
    val volume_id: Int,
    val book_id: Int,
    val chapter_id: Int,
    val verse_id: Int,
    val chapter_number: Int?,
    val verse_number: Int?,
    val scripture_text: String?,

    val volume_title: String?,
    val book_title: String?,
    val volume_long_title: String?,
    val book_long_title: String?,
    val volume_subtitle: String?,
    val book_subtitle: String?,
    val volume_short_title: String?,
    val book_short_title: String?,
    val volume_lds_url: String?,
    val book_lds_url: String?,
    val verse_title: String?,
    val verse_short_title: String?


    )
