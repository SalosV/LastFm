package com.example.data.remote.models

data class ArtistsSimilar(
    val name: String,
    val url: String,
    val image: List<ImageResponse>
)