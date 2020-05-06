package com.example.domain.usesCases.Artists.di.internal

import com.example.domain.repository.artists.ArtistsLocalRepository
import com.example.domain.usesCases.Artists.ArtistsUcImpl
import com.example.domain.repository.artists.ArtistsRepository
import com.example.domain.repository.detailArtist.DetailArtistRepository
import com.example.domain.usesCases.Artists.DetailArtistUcImpl
import dagger.Module
import dagger.Provides

@Module
internal class ArtistsUseCaseImplementation {

    @Provides
    fun artistsUseCaseImplProvides(
        artistsRepository: ArtistsRepository,
        artistsLocalRepository: ArtistsLocalRepository
    ) = ArtistsUcImpl(artistsRepository, artistsLocalRepository)

    @Provides
    fun detailArtistUseCaseImplProvides(
        detailArtistRepository: DetailArtistRepository
    ) = DetailArtistUcImpl(detailArtistRepository)
}