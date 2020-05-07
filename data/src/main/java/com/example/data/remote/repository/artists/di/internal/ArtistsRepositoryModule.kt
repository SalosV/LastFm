package com.example.data.remote.repository.artists.di.internal

import com.example.data.remote.repository.artists.ArtistsRepositoryImpl
import com.example.data.remote.repository.artists.DetailArtistRepositoryImp
import com.example.domain.repository.artists.ArtistsRepository
import com.example.domain.repository.detailArtist.DetailArtistRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ArtistsRepositoryImplementationModule::class])
internal abstract class ArtistsRepositoryModule {

    @Binds
    abstract fun artistsRepository(impl: ArtistsRepositoryImpl): ArtistsRepository

    @Binds
    abstract fun detailArtistRepository(impl: DetailArtistRepositoryImp): DetailArtistRepository
}