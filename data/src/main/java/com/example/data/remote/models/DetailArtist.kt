package com.example.data.remote.models

import com.example.domain.models.InformationArtist as InformationDomain
import com.example.domain.models.Description as DescriptionDomain

data class DetailArtist(
    val artist: InformationArtist
)

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

fun InformationArtist.toDomain() = InformationDomain(
    name,
    mbid,
    url,
    bio.fromDomain()
)

fun Description.fromDomain() = DescriptionDomain(
    published,
    summary,
    content
)