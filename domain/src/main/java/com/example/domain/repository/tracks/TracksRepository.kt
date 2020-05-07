package com.example.domain.repository.tracks

import com.example.domain.models.Track
import io.reactivex.Single

interface TracksRepository {
    fun getTracks(): Single<List<Track>>
    fun getSearchTracks(query: String): Single<List<Track>>
}