package com.example.churchwallpaperapplication.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Books(
    @PrimaryKey val id: Int,

    val volume_id: Int,
    val book_title: String,
    val book_long_title: String,
    @Ignore val book_subtitle: String,
    @Ignore val book_short_title: String,
    @Ignore val book_lds_url: String
)
