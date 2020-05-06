package com.example.lastfm.Artists.di

import com.example.data.DataApp
import com.example.domain.usesCases.Artists.ArtistsUc
import com.example.domain.usesCases.Artists.DetailArtistsUc
import com.example.lastfm.Artists.detailArtist.DetailArtistViewModel
import com.example.lastfm.Artists.listArtist.ArtistsViewModel
import dagger.Module
import dagger.Provides

@Module
class ArtistsViewModelModule {

    @Provides
    fun artistsViewModelProvides(artistsUc: ArtistsUc, app: DataApp) =
        ArtistsViewModel(artistsUc, app)

    @Provides
    fun detailArtistViewModelProvides(detailArtistsUc: DetailArtistsUc) =
        DetailArtistViewModel(detailArtistsUc)
}