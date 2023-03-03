package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT id FROM books WHERE volume_id LIKE :volume")
    fun getBookIdListFromVolumeId(volume: Int): List<Int>

    @Query("SELECT id FROM books WHERE book_title LIKE :book_title")
    fun getBookIdFromBookTitle(book_title: String): Int

    @Query("SELECT book_title FROM books WHERE volume_id LIKE :volume")
    fun getBookTitleList(volume: Int): List<String>

    @Query("SELECT book_title FROM books WHERE id LIKE :book_id")
    fun getBookTitleFromId(book_id: Int): String
}