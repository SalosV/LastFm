package com.example.domain.models

data class Track(
    val name: String,
    val duration: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val artist: TrackArtist,
    val image: List<Image>
)