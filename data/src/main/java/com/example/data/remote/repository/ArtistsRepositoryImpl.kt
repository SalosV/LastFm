package com.example.data.remote.repository

import com.example.data.DataApp
import com.example.data.remote.models.fromBase
import com.example.data.remote.models.toDomainModel
import com.example.data.remote.models.toEntityModel
import com.example.data.service.ServiceManager.service
import com.example.domain.models.BaseArtist
import com.example.domain.repository.artists.ArtistsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ArtistsRepositoryImpl(application: DataApp):
    ArtistsRepository {

    private val dataApp = application.database

    override fun getArtist(): Single<BaseArtist> =
        service.getArtists().flatMap {
            with(dataApp.getArtistsDAO()) {
                deleteAllArtists()
                    .andThen(insertArtist(it.fromBase().artist.map { it.toEntityModel() }))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy ()
            }

            Single.just(it.fromBase().toDomainModel())
        }
}