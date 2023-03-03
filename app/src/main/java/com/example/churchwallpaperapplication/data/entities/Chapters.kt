package com.example.churchwallpaperapplication.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Books::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("book_id"),
    onDelete = ForeignKey.CASCADE)
),
tableName = "chapters")
data class Chapters(
    @PrimaryKey val id: Int?,

    val book_id: Int?,
    val chapter_number: Int?
)
