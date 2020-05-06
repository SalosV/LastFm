package com.example.data.local.artists

import com.example.data.DataApp
import com.example.domain.models.Artists
import com.example.domain.repository.ArtistsLocalRepository
import io.reactivex.Single

class ArtistsLocalReposityImpl(application: DataApp) : ArtistsLocalRepository {

    private val dataApp = application.database

    override fun getArtist(): Single<List<Artists>> = run {
        with(dataApp.getArtistsDAO()) {
            getAllArtists().flatMap {
                Single.just(it.map { it.toDomain() })
            }
        }
    }
}