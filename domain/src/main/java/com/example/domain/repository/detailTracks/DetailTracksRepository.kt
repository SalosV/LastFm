package com.example.domain.repository.detailTracks

import com.example.domain.models.InformationTrack
import io.reactivex.Single

interface DetailTracksRepository {
    fun getInfTrack(artist: String, track: String): Single<InformationTrack>
}