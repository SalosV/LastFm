package com.example.lastfm.tracks.di

import com.example.lastfm.tracks.detailTrack.DetailTracksViewModel
import com.example.lastfm.tracks.listTracks.TracksViewModel
import dagger.Subcomponent

@Subcomponent(modules = [(TracksViewModelModule::class)])
interface TracksViewModelComponent {
    val tracksViewModel: TracksViewModel
    val detailTracksViewModel: DetailTracksViewModel
}