package com.example.domain.usesCases.Artists

import com.example.domain.models.InformationArtist
import com.example.domain.repository.detailArtist.DetailArtistRepository
import io.reactivex.Single

interface DetailArtistsUc {
    fun getInfoArtist(name: String, mbid: String): Single<InformationArtist>
}

class DetailArtistUcImpl(
    private val detailArtistRepository: DetailArtistRepository
) : DetailArtistsUc {

    override fun getInfoArtist(name: String, mbid: String) = run {
        detailArtistRepository.getInfoArtist(name, mbid)
    }
}