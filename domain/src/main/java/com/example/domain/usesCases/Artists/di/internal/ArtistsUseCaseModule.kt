package com.example.domain.usesCases.Artists.di.internal

import com.example.domain.usesCases.Artists.ArtistsUc
import com.example.domain.usesCases.Artists.ArtistsUcImpl
import com.example.domain.usesCases.Artists.DetailArtistUcImpl
import com.example.domain.usesCases.Artists.DetailArtistsUc
import dagger.Binds
import dagger.Module

@Module(includes = [ArtistsUseCaseImplementation::class])
internal abstract class ArtistsUseCaseModule {

    @Binds
    abstract fun artistsUseCaseProvide(impl: ArtistsUcImpl): ArtistsUc

    @Binds
    abstract fun detailArtistUseCaseProvide(impl: DetailArtistUcImpl): DetailArtistsUc
}