package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.artists.ArtistsDAO
import com.example.data.local.artists.ArtistsEntity
import com.example.data.local.database.converter.ImagesTypeConverts

private const val DATABASE_VERSION = 1

@Database(entities = [ArtistsEntity::class], version = DATABASE_VERSION)
@TypeConverters(
    ImagesTypeConverts::class
)
abstract class LastDatabase: RoomDatabase() {
    abstract fun getArtistsDAO(): ArtistsDAO
}