package com.example.lastfm.models

data class Artist(
    val name: String,
    val mbid: String,
    val image: List<Image>
)