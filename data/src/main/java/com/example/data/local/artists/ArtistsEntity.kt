package com.example.data.local.artists

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.remote.models.ImageResponse
import com.example.domain.models.Image as ImageDomain
import com.example.domain.models.Artists as ArtistsDomain

@Entity
data class ArtistsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val mbid: String = "",
    val name: String = "",
    val image: List<ImageResponse> = emptyList()
)

fun ArtistsEntity.toDomain() = ArtistsDomain(
    mbid = mbid,
    name = name,
    image = image.map {
        it.toDomain()
    }
)

fun ImageResponse.toDomain() = ImageDomain(
    text,
    size
)