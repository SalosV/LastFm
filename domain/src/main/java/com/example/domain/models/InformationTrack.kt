package com.example.domain.models

data class InformationTrack(
    val name: String,
    val url: String,
    val listeners: String,
    val artist: TrackArtist,
    val album: Album,
    val topTags: List<Tag>,
    val wiki: Description
)