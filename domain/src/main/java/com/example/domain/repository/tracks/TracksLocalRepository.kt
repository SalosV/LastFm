package com.example.domain.repository.tracks

import com.example.domain.models.Track
import io.reactivex.Single

interface TracksLocalRepository {
    fun getTracks(): Single<List<Track>>
    fun getSearchTacks(query: String): Single<List<Track>>
}