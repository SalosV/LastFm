package com.example.data.local.tracks.di.internal

import com.example.data.DataApp
import com.example.data.local.tracks.TracksLocalRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class TracksLocalRepositoryImplementation {

    @Provides
    fun tracksLocalRepositoryProvides(app: DataApp) =
        TracksLocalRepositoryImpl(app)
}