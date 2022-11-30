package com.example.churchwallpaperapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Verses(
    @PrimaryKey val id: Int,

    val chapter_id: Int,
    val verse_number: Int,
    val scripture_text: String
)
