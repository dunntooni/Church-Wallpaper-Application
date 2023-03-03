package com.example.churchwallpaperapplication.data.entities

import androidx.room.*

@Entity(tableName = "volumes")
data class Volumes(
    @PrimaryKey val id: Int?,

    val volume_title: String?,
    val volume_long_title: String?,
    val volume_subtitle: String?,
    val volume_short_title: String?,
    val volume_lds_url: String?
)
