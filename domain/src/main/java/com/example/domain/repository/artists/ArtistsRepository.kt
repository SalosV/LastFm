package com.example.domain.repository.artists

import com.example.domain.models.Artists
import com.example.domain.models.BaseArtist
import io.reactivex.Single

interface ArtistsRepository {
    fun getArtist(): Single<BaseArtist>
    fun getSearchArtists(query: String): Single<List<Artists>>
}