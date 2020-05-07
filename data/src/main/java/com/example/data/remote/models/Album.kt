package com.example.data.remote.models

data class Album(
    val artist: String,
    val title: String,
    val mbid: String,
    val url: String,
    val image: List<ImageResponse>
)