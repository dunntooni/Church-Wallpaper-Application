package com.example.churchwallpaperapplication.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Chapters::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("chapter_id"),
    onDelete = ForeignKey.CASCADE)
),
tableName = "verses")
data class Verses(
    @PrimaryKey val id: Int?,

    val chapter_id: Int?,
    val verse_number: Int?,
    val scripture_text: String?
)
