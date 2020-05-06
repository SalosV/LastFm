package com.example.domain.models

data class Artists (
    val name: String,
    val listeners: String = "",
    val mbid: String,
    val url: String = "",
    val streamable: String = "",
    val image: List<Image>
)
