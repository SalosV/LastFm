package com.example.data.local.tracks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TracksDAO {

    @Insert(onConflict = IGNORE)
    fun insertTrack(artist: List<TracksEntity>): Completable

    @Query(value = "SELECT * FROM TracksEntity")
    fun getAllTracks(): Single<List<TracksEntity>>

    @Query("DELETE FROM TracksEntity")
    fun deleteAllTracks(): Completable

    @Query(value = "SELECT * FROM TracksEntity WHERE name LIKE :query")
    fun searchTrack(query: String): Single<List<TracksEntity>>
}