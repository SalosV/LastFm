package com.example.data.remote.models

data class Result<T>(
    val results: T
)

data class ArtistMatches(
    val artistmatches: InformationMatches
)

data class InformationMatches(
    val artist: List<ArtistsResponse>
)

data class TrackMatches(
    val trackmatches: InformationMatchesTracks
)

data class InformationMatchesTracks(
    val track: List<TracksSearch>
)