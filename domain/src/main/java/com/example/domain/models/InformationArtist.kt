package com.example.domain.models

data class InformationArtist(
    val name: String,
    val mbid: String,
    val url: String,
    val bio: Description,
    val tags: List<Tag>,
    val similarArtists: List<ArtistsSimilar>
)

data class Description(
    val published: String,
    val summary: String,
    val content: String
)

data class Tag(
    val name: String,
    val url: String
)

