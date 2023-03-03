package com.example.churchwallpaperapplication.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Volumes::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("volume_id"),
    onDelete = ForeignKey.CASCADE)),
tableName = "books")
data class Books(
    @PrimaryKey val id: Int?,

    val volume_id: Int?,
    val book_title: String?,
    val book_long_title: String?,
    val book_subtitle: String?,
    val book_short_title: String?,
    val book_lds_url: String?
)
