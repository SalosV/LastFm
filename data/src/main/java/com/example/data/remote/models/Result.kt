package com.example.data.remote.models

data class Result(
    val results: ArtistMatches
)

data class ArtistMatches(
    val artistmatches: InformationMatchs
)

data class InformationMatchs(
    val artist: List<ArtistsResponse>
)
