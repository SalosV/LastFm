package com.example.domain.repository

import com.example.domain.models.Artists
import io.reactivex.Single

interface ArtistsLocalRepository {
    fun getArtist(): Single<List<Artists>>
}