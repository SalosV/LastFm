package com.example.lastfm.tracks.di

import com.example.lastfm.tracks.listTracks.TracksViewModel
import dagger.Subcomponent

@Subcomponent(modules = [(TracksViewModelModule::class)])
interface TracksViewModelComponent {
    val tracksViewModel: TracksViewModel
}