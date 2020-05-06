package com.example.domain.repository.detailArtist

import com.example.domain.models.InformationArtist
import io.reactivex.Single

interface DetailArtistRepository {
    fun getInfoArtist(name: String, mbid: String): Single<InformationArtist>
}