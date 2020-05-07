package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.artists.ArtistsDAO
import com.example.data.local.artists.ArtistsEntity
import com.example.data.local.database.converter.ImagesTypeConverts
import com.example.data.local.database.converter.TrackArtistTypeConverts
import com.example.data.local.tracks.TracksDAO
import com.example.data.local.tracks.TracksEntity

private const val DATABASE_VERSION = 5

@Database(
    entities = [
        ArtistsEntity::class,
        TracksEntity::class
    ], version = DATABASE_VERSION, exportSchema = false
)
@TypeConverters(
    ImagesTypeConverts::class,
    TrackArtistTypeConverts::class
)
abstract class LastDatabase : RoomDatabase() {
    abstract fun getArtistsDAO(): ArtistsDAO
    abstract fun getTracksDAO(): TracksDAO
}