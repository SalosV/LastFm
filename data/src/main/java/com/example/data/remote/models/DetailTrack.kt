package com.example.data.remote.models

import com.example.data.local.tracks.toDomain
import com.google.gson.annotations.SerializedName
import com.example.domain.models.InformationTrack as InformationTrackDomain
import com.example.domain.models.Album as AlbumDomain

data class DetailTrack(
    val track: InformationTrack
)

data class InformationTrack(
    val name: String,
    val url: String,
    val listeners: String,
    val artist: TrackArtist,
    val album: Album,
    @SerializedName("toptags")
    val topTags: Tags,
    val wiki: DescriptionInformationArtist
)

fun InformationTrack.toDomain() = InformationTrackDomain(
    name,
    url,
    listeners,
    artist.toDomain(),
    album.toDomain(),
    topTags.tag.map {
        it.toDomain()
    },
    wiki.fromDomain()
)

fun Album.toDomain() = AlbumDomain(
    artist,
    title,
    mbid,
    url,
    image.map { it.toDomain() }
)