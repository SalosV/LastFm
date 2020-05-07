package com.example.lastfm.tracks.di

import com.example.data.DataApp
import com.example.domain.usesCases.tracks.DetailTrackCaseUse
import com.example.domain.usesCases.tracks.TracksUseCase
import com.example.lastfm.tracks.detailTrack.DetailTracksViewModel
import com.example.lastfm.tracks.listTracks.TracksViewModel
import dagger.Module
import dagger.Provides


@Module
class TracksViewModelModule {

    @Provides
    fun tracksViewModelProvides(tracksUseCase: TracksUseCase, app: DataApp) =
        TracksViewModel(tracksUseCase, app)

    @Provides
    fun detailTrackViewModelProvides(detailTrackCaseUse: DetailTrackCaseUse) =
        DetailTracksViewModel(detailTrackCaseUse)
}