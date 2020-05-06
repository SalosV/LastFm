package com.example.data.local.artists.di.internal

import com.example.data.DataApp
import com.example.data.local.artists.ArtistsLocalReposityImpl
import dagger.Module
import dagger.Provides

@Module
class ArtistsLocalRepositoryImplementationModule {

    @Provides
    fun artistsLocalRepositoryProvides(app: DataApp) = ArtistsLocalReposityImpl(app)
}