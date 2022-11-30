package com.example.churchwallpaperapplication.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Volumes(
    @PrimaryKey val id: Int,

    val volume_title: String,
    val long_title: String,
    @Ignore val volume_subtitle: String?,
    @Ignore val volume_short_title: String?,
    @Ignore val volume_lds_url: String?
)
