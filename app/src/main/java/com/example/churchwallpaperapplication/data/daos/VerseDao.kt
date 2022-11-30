package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VerseDao {
    @Query("SELECT id FROM verses WHERE chapter_id LIKE :chapter")
    fun getVerseIdList(chapter: Int): List<Int>
}