package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VerseDao {
    @Query("SELECT id FROM verses WHERE chapter_id LIKE :chapter_id")
    fun getVerseIdListFromChapterId(chapter_id: Int): List<Int>

    @Query("SELECT id FROM verses WHERE verse_number LIKE :verse AND chapter_id LIKE :chapter_id")
    fun getVerseIdFromVerseNumberAndChapterId(verse: Int, chapter_id: Int): Int

    @Query("SELECT verse_number FROM verses WHERE chapter_id LIKE :chapter_id")
    fun getVerseNumberListFromChapterId(chapter_id: Int): List<Int>

    @Query("SELECT verse_number FROM verses WHERE id LIKE :verse_id")
    fun getVerseNumberListFromVerseId(verse_id: Int): List<Int>

    @Query("SELECT scripture_text FROM verses WHERE chapter_id LIKE :chapter_id")
    fun getVerseTextListFromChapterId(chapter_id: Int): List<String>

    @Query("SELECT scripture_text FROM verses WHERE id LIKE :verse_id")
    fun getVerseTextFromVerseId(verse_id: Int): String
}