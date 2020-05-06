package com.example.domain.repository.artists

import com.example.domain.models.Artists
import io.reactivex.Single

interface ArtistsLocalRepository {
    fun getArtist(): Single<List<Artists>>
}