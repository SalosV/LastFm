package com.example.lastfm.Artists.di

import com.example.lastfm.Artists.listArtist.ArtistsViewModel
import dagger.Subcomponent

@Subcomponent(modules = [(ArtistsViewModelModule::class)])
interface ArtistsViewModelComponent {
    val artistsViewModel: ArtistsViewModel
}