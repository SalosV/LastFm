package com.example.lastfm.di

import com.example.data.DataApp
import com.example.data.di.DataModule
import com.example.domain.di.DomainModule
import com.example.lastfm.Artists.di.ArtistsViewModelComponent
import com.example.lastfm.Artists.di.ArtistsViewModelModule
import com.example.lastfm.tracks.di.TracksViewModelComponent
import com.example.lastfm.tracks.di.TracksViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, DataModule::class])
interface ApplicationComponent {

    fun plus(module: ArtistsViewModelModule): ArtistsViewModelComponent

    fun plusTracks(module: TracksViewModelModule): TracksViewModelComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: DataApp): ApplicationComponent
    }
}