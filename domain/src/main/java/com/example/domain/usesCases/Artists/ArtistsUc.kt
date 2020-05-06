package com.example.domain.usesCases.Artists

import com.example.domain.models.Artists
import com.example.domain.repository.artists.ArtistsLocalRepository
import com.example.domain.repository.artists.ArtistsRepository
import io.reactivex.Single

interface ArtistsUc {
    fun getArtists(): Single<List<Artists>>
    fun getArtistsLocal(): Single<List<Artists>>
    fun getSearchArtistsLocal(query: String): Single<List<Artists>>
    fun getSearchArtists(query: String): Single<List<Artists>>
}

class ArtistsUcImpl(
    private val artistsRepository: ArtistsRepository,
    private val artistsLocalRepository: ArtistsLocalRepository
) : ArtistsUc {

    override fun getArtists() = run {
        artistsRepository.getArtist().map {
            it.artist
        }
    }

    override fun getArtistsLocal() = run {
        artistsLocalRepository.getArtist()
    }

    override fun getSearchArtistsLocal(query: String): Single<List<Artists>> = run {
        artistsLocalRepository.getSearchArtist(query)
    }

    override fun getSearchArtists(query: String): Single<List<Artists>> = run {
        artistsRepository.getSearchArtists(query)
    }

}
