package com.example.domain.usesCases.Artists.di.internal

import com.example.domain.usesCases.Artists.ArtistsUc
import com.example.domain.usesCases.Artists.ArtistsUcImpl
import dagger.Binds
import dagger.Module

@Module(includes = [ArtistsUseCaseImplementation::class])
internal abstract class ArtistsUseCaseModule {

    @Binds
    abstract fun artistsUseCaseProvide(impl: ArtistsUcImpl): ArtistsUc
}