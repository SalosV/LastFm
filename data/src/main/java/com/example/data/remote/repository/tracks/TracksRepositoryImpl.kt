package com.example.data.remote.repository.tracks

import com.example.data.DataApp
import com.example.data.remote.models.toDomainModel
import com.example.data.service.ServiceManager.service
import com.example.domain.repository.tracks.TracksRepository
import io.reactivex.Single

class TracksRepositoryImpl(application: DataApp): TracksRepository {

    private val dataApp = application.database

    override fun getTracks() =
        service.getTracks().flatMap {
            Single.just(it.tracks.track.map { it.toDomainModel() })
        }
}