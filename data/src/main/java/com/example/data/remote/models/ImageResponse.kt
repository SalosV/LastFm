package com.example.data.remote.models

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("#text")
    val text: String,
    val size: String
)