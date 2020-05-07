package com.example.domain.usesCases.tracks

import com.example.domain.models.InformationTrack
import com.example.domain.repository.detailTracks.DetailTracksRepository
import io.reactivex.Single

interface DetailTrackCaseUse {
    fun getInfoTrack(artist: String, track: String): Single<InformationTrack>
}

class DetailTrackCaseUseImpl(private val detailTracksRepository: DetailTracksRepository) :
    DetailTrackCaseUse {

    override fun getInfoTrack(artist: String, track: String): Single<InformationTrack> =
        detailTracksRepository.getInfTrack(artist, track)
}