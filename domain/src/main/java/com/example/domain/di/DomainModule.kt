package com.example.domain.di

import com.example.domain.usesCases.Artists.di.internal.ArtistsUseCaseModule
import com.example.domain.usesCases.tracks.di.internal.TracksUseCaseModule
import dagger.Module

@Module(
    includes = [
        ArtistsUseCaseModule::class,
        TracksUseCaseModule::class
    ]
)
class DomainModule