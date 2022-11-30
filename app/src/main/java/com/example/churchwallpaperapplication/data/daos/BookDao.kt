package com.example.churchwallpaperapplication.data.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT id FROM books WHERE volume_id LIKE :volume")
    fun getBookIdList(volume: Int): List<Int>

    @Query("SELECT book_title FROM books WHERE volume_id LIKE :volume")
    fun getBookTitleList(volume: Int): List<String>
}