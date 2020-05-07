package com.example.data.local.tracks

import com.example.data.DataApp
import com.example.domain.models.Track
import com.example.domain.repository.tracks.TracksLocalRepository
import io.reactivex.Single

class TracksLocalRepositoryImpl(application: DataApp) : TracksLocalRepository {

    private val dataApp = application.database

    override fun getTracks(): Single<List<Track>> = run {
        with(dataApp.getTracksDAO()) {
            getAllTracks().flatMap {
                Single.just(it.map { it.toDomain() })
            }
        }
    }
}