package com.example.domain.usesCases.Artists.di.internal

import com.example.domain.repository.ArtistsLocalRepository
import com.example.domain.usesCases.Artists.ArtistsUcImpl
import com.example.domain.repository.ArtistsRepository
import dagger.Module
import dagger.Provides

@Module
internal class ArtistsUseCaseImplementation {

    @Provides
    fun artistsUseCaseImplProvides(
        artistsRepository: ArtistsRepository,
        artistsLocalRepository: ArtistsLocalRepository
    ) = ArtistsUcImpl(artistsRepository, artistsLocalRepository)
}