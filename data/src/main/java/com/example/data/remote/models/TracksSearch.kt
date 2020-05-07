package com.example.data.remote.models

import com.example.domain.models.Track
import com.example.domain.models.TrackArtist

data class TracksSearch(
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val artist: String,
    val image: List<ImageResponse>
)

fun TracksSearch.toDomain() = Track(
    name,
    "",
    listeners,
    mbid,
    url,
    artist = TrackArtist(artist, "", ""),
    image = image.map {
        it.toDomainModel()
    }
)