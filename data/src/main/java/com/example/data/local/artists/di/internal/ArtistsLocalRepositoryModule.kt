package com.example.data.local.artists.di.internal

import com.example.data.local.artists.ArtistsLocalReposityImpl
import com.example.domain.repository.ArtistsLocalRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ArtistsLocalRepositoryImplementationModule::class])
internal abstract class ArtistsLocalRepositoryModule {

    @Binds
    abstract fun artistsLocalRepository(impl: ArtistsLocalReposityImpl): ArtistsLocalRepository
}