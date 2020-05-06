package com.example.data.remote.models

import com.example.domain.models.BaseArtist as BaseArtistDomain
import com.example.domain.models.CountsPages as CountPagesDomain
import com.example.domain.models.Artists as ArtistDomain
import com.example.domain.models.Image as ImageDomain
import com.google.gson.annotations.SerializedName

data class ArtistsBase(
    val artist: List<ArtistsResponse>,
    @SerializedName("@attr") val ttr: CountsPages
)

fun ArtistsBase.toDomainModel() = BaseArtistDomain(
    artist = artist.map {
        it.toDomainModel()
    },
    countPages = ttr.toDomainModel()
)

fun CountsPages.toDomainModel() = CountPagesDomain(
    page,
    perPage,
    totalPages,
    total
)

fun ArtistsResponse.toDomainModel() = ArtistDomain(
    name,
    listeners,
    mbid,
    url,
    streamable,
    image = image.map {
        it.toDomainModel()
    }
)

fun ImageResponse.toDomainModel() = ImageDomain(
    text,
    size
)