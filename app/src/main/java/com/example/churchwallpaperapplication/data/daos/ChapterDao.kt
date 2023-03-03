package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ChapterDao {
    @Query("SELECT id FROM chapters WHERE book_id LIKE :book_id")
    fun getChapterIdListFromBookId(book_id: Int): List<Int>

    @Query("SELECT id FROM chapters WHERE chapter_number LIKE :chapter AND book_id LIKE :book_id")
    fun getChapterIdFromChapterNumberAndBookId(chapter: Int, book_id: Int): Int

    @Query("SELECT chapter_number FROM chapters WHERE book_id LIKE :book_id")
    fun getChapterNumbersListFromBookId(book_id: Int): List<Int>

    @Query("SELECT chapter_number FROM chapters WHERE id LIKE :chapter_id")
    fun getChapterNumbersListFromChapterId(chapter_id: Int): List<Int>
}