package com.example.domain.repository.artists

import com.example.domain.models.BaseArtist
import io.reactivex.Single

interface ArtistsRepository {
    fun getArtist(): Single<BaseArtist>
}