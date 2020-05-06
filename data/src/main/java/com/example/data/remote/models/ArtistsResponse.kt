package com.example.data.remote.models

import com.example.data.local.artists.ArtistsEntity

data class ArtistsResponse(
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<ImageResponse>
)

fun ArtistsResponse.toEntityModel() = ArtistsEntity(
    name = name,
    mbid = mbid,
    image = image
)