package com.example.lastfm.tracks.di

import com.example.domain.usesCases.tracks.TracksUseCase
import com.example.lastfm.tracks.listTracks.TracksViewModel
import dagger.Module
import dagger.Provides


@Module
class TracksViewModelModule {

    @Provides
    fun tracksViewModelProvides(tracksUseCase: TracksUseCase) =
        TracksViewModel(tracksUseCase)
}