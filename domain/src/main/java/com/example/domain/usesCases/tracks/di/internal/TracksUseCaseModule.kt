package com.example.domain.usesCases.tracks.di.internal

import com.example.domain.usesCases.tracks.TracksUseCase
import com.example.domain.usesCases.tracks.TracksUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [TracksUseCaseImplementation::class])
internal abstract class TracksUseCaseModule {

    @Binds
    abstract fun tracksUseCaseProvide(impl: TracksUseCaseImpl): TracksUseCase
}