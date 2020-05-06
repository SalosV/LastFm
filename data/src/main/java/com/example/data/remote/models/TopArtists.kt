package com.example.data.remote.models


data class TopArtists(
    val topartists: ArtistsBase
)

fun TopArtists.fromBase() = ArtistsBase(
    artist = topartists.artist,
    ttr = topartists.ttr
)
