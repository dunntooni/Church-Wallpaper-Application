package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ChapterDao {
    @Query("SELECT id FROM chapters WHERE book_id LIKE :book")
    fun getChapterIdList(book: Int): List<Int>
}