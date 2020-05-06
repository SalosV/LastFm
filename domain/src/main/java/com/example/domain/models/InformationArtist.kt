package com.example.domain.models

data class InformationArtist(
    val name: String,
    val mbid: String,
    val url: String,
    val bio: Description
)

data class Description(
    val published: String,
    val summary: String,
    val content: String
)