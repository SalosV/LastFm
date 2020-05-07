package com.example.lastfm.tracks.listTracks

import com.example.domain.models.Track

sealed class TracksState{
    object Loading: TracksState()
    class ShowTracks(val tracks: List<Track>): TracksState()
    class Error(val msg: String): TracksState()
}