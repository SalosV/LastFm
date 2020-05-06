package com.example.data.remote.repository

import com.example.data.DataApp
import com.example.data.remote.models.toDomain
import com.example.data.service.ServiceManager.service
import com.example.domain.models.InformationArtist
import com.example.domain.repository.detailArtist.DetailArtistRepository
import io.reactivex.Single

class DetailArtistRepositoryImp(application: DataApp): DetailArtistRepository {

    private val dataApp = application.database

    override fun getInfoArtist(name: String, mbid: String): Single<InformationArtist> = run {
        service.getDetailArtist(artist = name, mbid = mbid).flatMap {
            Single.just(it.artist.toDomain())
        }
    }
}

