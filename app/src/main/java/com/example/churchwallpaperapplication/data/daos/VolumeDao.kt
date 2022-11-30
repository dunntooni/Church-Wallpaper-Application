package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VolumeDao {
    @Query("SELECT id FROM volumes")
    fun getVolumeIdList(): List<Int>

    @Query("SELECT volume_title FROM volumes")
    fun getVolumeTitleList(): List<String>
}