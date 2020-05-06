package com.example.data.local.artists

import com.example.data.DataApp
import com.example.domain.models.Artists
import com.example.domain.repository.artists.ArtistsLocalRepository
import io.reactivex.Single

class ArtistsLocalReposityImpl(application: DataApp) :
    ArtistsLocalRepository {

    private val dataApp = application.database

    override fun getArtist(): Single<List<Artists>> = run {
        with(dataApp.getArtistsDAO()) {
            getAllArtists().flatMap {
                Single.just(it.map { it.toDomain() })
            }
        }
    }

    override fun getSearchArtist(query: String): Single<List<Artists>> = run {
        with(dataApp.getArtistsDAO()) {
            searchArtist("%$query%").flatMap {
                Single.just(it.map { it.toDomain() })
            }
        }
    }
}