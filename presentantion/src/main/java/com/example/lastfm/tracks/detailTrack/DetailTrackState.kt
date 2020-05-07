package com.example.lastfm.tracks.detailTrack

import com.example.domain.models.InformationTrack

sealed class DetailTrackState {
    object Loading: DetailTrackState()
    class ShowInformationTrack(val track: InformationTrack): DetailTrackState()
    class Error(val msg: String): DetailTrackState()
}