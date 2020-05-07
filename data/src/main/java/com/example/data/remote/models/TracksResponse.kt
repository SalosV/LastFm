package com.example.data.remote.models

import com.google.gson.annotations.SerializedName

data class TracksResponse(
    val tracks: TracksData
)

data class TracksData(
    val track : List<Track>,
    @SerializedName("@attr")
    val attr: CountsPages
)

