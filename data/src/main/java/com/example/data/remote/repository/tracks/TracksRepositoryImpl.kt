package com.example.data.remote.repository.tracks

import com.example.data.DataApp
import com.example.data.remote.models.toDomain
import com.example.data.remote.models.toDomainModel
import com.example.data.remote.models.toEntityModel
import com.example.data.service.ServiceManager.service
import com.example.domain.models.Track
import com.example.domain.repository.tracks.TracksRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class TracksRepositoryImpl(application: DataApp): TracksRepository {

    private val dataApp = application.database

    override fun getTracks() =
        service.getTracks().flatMap {
            with(dataApp.getTracksDAO()) {
                deleteAllTracks()
                    .andThen(insertTrack(it.tracks.track.map { it.toEntityModel()}))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy()
            }

            Single.just(it.tracks.track.map { it.toDomainModel() })
        }

    override fun getSearchTracks(query: String): Single<List<Track>> = run {
        service.getSearchTracks(query).flatMap {
            Single.just(it.results.trackmatches.track.map {
                it.toDomain()
            })
        }
    }
}