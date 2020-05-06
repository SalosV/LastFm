package com.example.data.remote.repository.artists.di.internal

import com.example.data.remote.repository.ArtistsRepositoryImpl
import com.example.domain.repository.ArtistsRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ArtistsRepositoryImplementationModule::class])
internal abstract class ArtistsRepositoryModule {

    @Binds
    abstract fun artistsRepository(impl: ArtistsRepositoryImpl): ArtistsRepository
}