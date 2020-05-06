package com.example.lastfm.Artists.detailArtist

import com.example.domain.models.InformationArtist

sealed class DetailArtistState {
    object Loading: DetailArtistState()
    class ShowInformationArtist(val artist: InformationArtist): DetailArtistState()
    class Error(val msg: String): DetailArtistState()
}