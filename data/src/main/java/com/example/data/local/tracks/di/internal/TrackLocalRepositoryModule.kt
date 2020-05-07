package com.example.data.local.tracks.di.internal

import com.example.data.local.tracks.TracksLocalRepositoryImpl
import com.example.domain.repository.tracks.TracksLocalRepository
import dagger.Binds
import dagger.Module

@Module(includes = [TracksLocalRepositoryImplementation::class])
internal abstract class TrackLocalRepositoryModule {

    @Binds
    abstract fun tracksLocalRepository(impl: TracksLocalRepositoryImpl): TracksLocalRepository
}