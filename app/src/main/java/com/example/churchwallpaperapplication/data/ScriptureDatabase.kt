package com.example.churchwallpaperapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.churchwallpaperapplication.data.daos.*
import com.example.churchwallpaperapplication.data.entities.*

@Database(
    version = 3,
    entities =
    [Volumes::class,
        Books::class,
        Chapters::class,
        Verses::class,],
//    views = [Scriptures::class],
    exportSchema = true
)
abstract class ScriptureDatabase : RoomDatabase() {
    abstract fun BookDao() : BookDao
    abstract fun VolumeDao() : VolumeDao
    abstract fun ChapterDao() : ChapterDao
    abstract fun VerseDao() : VerseDao
//    abstract fun ScriptureDao() : ScriptureDao
}