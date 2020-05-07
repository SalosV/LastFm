package com.example.data.remote.repository.tracks.di.internal

import com.example.data.DataApp
import com.example.data.remote.repository.tracks.TracksRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class TracksRepositoryImplementation {

    @Provides
    fun tracksRepositoryImpl(app: DataApp) =
        TracksRepositoryImpl(app)
}