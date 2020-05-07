package com.example.domain.usesCases.tracks.di.internal

import com.example.domain.repository.detailTracks.DetailTracksRepository
import com.example.domain.repository.tracks.TracksLocalRepository
import com.example.domain.repository.tracks.TracksRepository
import com.example.domain.usesCases.tracks.DetailTrackCaseUseImpl
import com.example.domain.usesCases.tracks.TracksUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
internal class TracksUseCaseImplementation {

    @Provides
    fun tracksUseCaseImplProvides(
        tracksRepository: TracksRepository,
        tracksLocalRepository: TracksLocalRepository
    ) =
        TracksUseCaseImpl(tracksRepository, tracksLocalRepository)

    @Provides
    fun detailTrackUseCaseImplProvides(detailTracksRepository: DetailTracksRepository) =
        DetailTrackCaseUseImpl(detailTracksRepository)
}