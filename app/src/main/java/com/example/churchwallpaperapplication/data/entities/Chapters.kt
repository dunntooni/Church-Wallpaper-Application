package com.example.churchwallpaperapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Chapters(
    @PrimaryKey val id: Int,

    val book_id: Int,
    val chapter_number: Int
)
