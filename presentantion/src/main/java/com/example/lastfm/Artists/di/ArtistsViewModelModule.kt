package com.example.lastfm.Artists.di

import com.example.data.DataApp
import com.example.domain.usesCases.Artists.ArtistsUc
import com.example.lastfm.Artists.listArtist.ArtistsViewModel
import dagger.Module
import dagger.Provides

@Module
class ArtistsViewModelModule {
    @Provides
    fun artistsViewModelProvides(artistsUc: ArtistsUc, app: DataApp) =
        ArtistsViewModel(artistsUc, app)
}