package com.example.domain.di

import com.example.domain.usesCases.Artists.di.internal.ArtistsUseCaseModule
import dagger.Module

@Module(includes = [ArtistsUseCaseModule::class])
class DomainModule