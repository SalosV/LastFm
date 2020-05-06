package com.example.data.remote.repository.artists.di.internal

import com.example.data.DataApp
import com.example.data.remote.repository.ArtistsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ArtistsRepositoryImplementationModule {

    @Provides
    fun artistsRepositoryImpl(app: DataApp) = ArtistsRepositoryImpl(app)
}