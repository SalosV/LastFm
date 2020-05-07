package com.example.domain.usesCases.tracks.di.internal

import com.example.domain.usesCases.tracks.DetailTrackCaseUse
import com.example.domain.usesCases.tracks.DetailTrackCaseUseImpl
import com.example.domain.usesCases.tracks.TracksUseCase
import com.example.domain.usesCases.tracks.TracksUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [TracksUseCaseImplementation::class])
internal abstract class TracksUseCaseModule {

    @Binds
    abstract fun tracksUseCaseProvide(impl: TracksUseCaseImpl): TracksUseCase

    @Binds
    abstract fun detailTracksUseCaseProvide(impl: DetailTrackCaseUseImpl): DetailTrackCaseUse
}