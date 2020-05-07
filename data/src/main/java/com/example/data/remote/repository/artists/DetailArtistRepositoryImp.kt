package com.example.data.remote.repository.artists

import com.example.data.remote.models.toDomain
import com.example.data.service.ServiceManager.service
import com.example.domain.models.InformationArtist
import com.example.domain.repository.detailArtist.DetailArtistRepository
import io.reactivex.Single

class DetailArtistRepositoryImp: DetailArtistRepository {

    override fun getInfoArtist(name: String, mbid: String): Single<InformationArtist> = run {
        service.getDetailArtist(artist = name, mbid = mbid).flatMap {
            Single.just(it.artist.toDomain())
        }
    }
}

