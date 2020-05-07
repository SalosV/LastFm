package com.example.data.remote.repository.tracks.di.internal

import com.example.data.remote.repository.tracks.DetailTrackRepositoryImpl
import com.example.data.remote.repository.tracks.TracksRepositoryImpl
import com.example.domain.repository.detailTracks.DetailTracksRepository
import com.example.domain.repository.tracks.TracksRepository
import dagger.Binds
import dagger.Module

@Module(includes = [TracksRepositoryImplementation::class])
internal abstract class TracksRepositoryModule {

    @Binds
    abstract fun tracksRepository(impl: TracksRepositoryImpl): TracksRepository

    @Binds
    abstract fun detailTrackRepository(impl: DetailTrackRepositoryImpl): DetailTracksRepository
}