package com.example.lastfm.Artists.listArtist

import com.example.domain.models.Artists

sealed class ArtistsState {
    object Loading: ArtistsState()
    class ShowArtists(val artists: List<Artists>): ArtistsState()
    class Error(val msg: String): ArtistsState()
}