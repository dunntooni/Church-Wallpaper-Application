package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScriptureDao {
    @Query("SELECT scripture_text FROM scriptures WHERE volume_id LIKE :volume AND " +
            "book_id LIKE :book AND chapter_id LIKE :chapter AND verse_id LIKE :verse")
    fun getByIds(volume: Int, book: Int, chapter: Int, verse: Int): String
}