package com.example.data.remote.models

import com.example.domain.models.InformationArtist as InformationDomain
import com.example.domain.models.ArtistsSimilar as ArtistsSimilarDomain
import com.example.domain.models.Description as DescriptionDomain
import com.example.domain.models.Tag as TagDomain

data class DetailArtist(
    val artist: InformationArtist
)

data class InformationArtist(
    val name: String,
    val mbid: String,
    val url: String,
    val bio: DescriptionInformationArtist,
    val tags: Tags,
    val similar: Similar
)

data class Tags(
    val tag: List<Tag>
)

data class Similar(
    val artist: List<ArtistsSimilar>
)

//Mappers

fun InformationArtist.toDomain() = InformationDomain(
    name,
    mbid,
    url,
    bio.fromDomain(),
    tags.tag.map {
        it.toDomain()
    },
    similar.artist.map {
        it.toDomain()
    }
)

fun DescriptionInformationArtist.fromDomain() = DescriptionDomain(
    published,
    summary,
    content
)

fun Tag.toDomain() = TagDomain(
    name,
    url
)

fun ArtistsSimilar.toDomain() = ArtistsSimilarDomain(
    name,
    url,
    image.map {
        it.toDomainModel()
    }
)