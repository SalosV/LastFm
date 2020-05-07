package com.example.data.local.tracks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.remote.models.ImageResponse
import com.example.domain.models.Track as TrackDomain
import com.example.domain.models.TrackArtist as TrackArtistDomain
import com.example.domain.models.Image as ImageDomain
import com.example.data.remote.models.TrackArtist

@Entity
data class TracksEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val duration: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val artist: TrackArtist,
    val image: List<ImageResponse>
)

fun TracksEntity.toDomain() = TrackDomain(
    name = name,
    duration = duration,
    listeners = listeners,
    mbid = mbid,
    url = url,
    artist = artist.toDomain(),
    image = image.map {
        it.toDomain()
    }
)

fun TrackArtist.toDomain() = TrackArtistDomain(
    name,
    mbid,
    url
)

fun ImageResponse.toDomain() = ImageDomain(
    text,
    size
)