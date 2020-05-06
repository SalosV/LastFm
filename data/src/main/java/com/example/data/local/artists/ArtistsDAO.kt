package com.example.data.local.artists

import androidx.room.Dao
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ArtistsDAO  {

    @Insert(onConflict = IGNORE)
    fun insertArtist(artist: List<ArtistsEntity>): Completable

    @Query(value = "SELECT * FROM ArtistsEntity")
    fun getAllArtists(): Single<List<ArtistsEntity>>

    @Query("DELETE FROM ArtistsEntity")
    fun deleteAllArtists(): Completable
}