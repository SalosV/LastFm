package com.example.data.di

import com.example.data.local.artists.di.internal.ArtistsLocalRepositoryModule
import com.example.data.remote.repository.artists.di.internal.ArtistsRepositoryModule
import com.example.data.remote.repository.tracks.di.internal.TracksRepositoryModule
import dagger.Module

@Module(
    includes = [
        ArtistsRepositoryModule::class,
        ArtistsLocalRepositoryModule::class,
        TracksRepositoryModule::class
    ]
)
class DataModule