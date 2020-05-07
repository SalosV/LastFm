package com.example.data.remote.repository.tracks

import com.example.data.remote.models.toDomain
import com.example.data.service.ServiceManager.service
import com.example.domain.repository.detailTracks.DetailTracksRepository
import io.reactivex.Single

class DetailTrackRepositoryImpl: DetailTracksRepository {

    override fun getInfTrack(artist: String, track: String) = run {
        service.getDetailTrack(artist, track).flatMap {
            Single.just(it.track.toDomain())
        }
    }
}