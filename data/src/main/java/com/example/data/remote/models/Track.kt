package com.example.data.remote.models

import com.example.domain.models.Track as TrackDomain
import com.example.domain.models.TrackArtist as TrackArtistDomain

data class Track(
    val name: String,
    val duration: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val artist: TrackArtist,
    val image: List<ImageResponse>
)

fun Track.toDomainModel() = TrackDomain(
    name,
    duration,
    listeners,
    mbid,
    url,
    artist.toDomain(),
    image.map {
        it.toDomainModel()
    }
)

fun TrackArtist.toDomain() = TrackArtistDomain(
    name,
    mbid,
    url
)
