package com.example.domain.models

data class InformationArtist(
    val name: String,
    val mbid: String,
    val url: String,
    val bio: Description,
    val tags: List<Tag>,
    val similarArtists: List<ArtistsSimilar>
)

