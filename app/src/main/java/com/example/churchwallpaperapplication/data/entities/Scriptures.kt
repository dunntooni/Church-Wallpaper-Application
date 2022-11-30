package com.example.churchwallpaperapplication.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Scriptures(
    @PrimaryKey val id: Int,

    val volume_id: Int,
    val book_id: Int,
    val chapter_id: Int,
    val verse_id: Int,
    val chapter_number: Int,
    val verse_number: Int,
    val scripture_text: String,

    @Ignore val volume_title: String,
    @Ignore val book_title: String,
    @Ignore val volume_long_title: String,
    @Ignore val book_long_title: String,
    @Ignore val volume_subtitle: String,
    @Ignore val book_subtitle: String,
    @Ignore val volume_short_title: String,
    @Ignore val volume_lds_url: String,
    @Ignore val book_lds_url: String,
    @Ignore val verse_title: String?,
    @Ignore val verse_short_title: String?


    )
