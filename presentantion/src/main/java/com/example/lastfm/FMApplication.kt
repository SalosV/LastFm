package com.example.lastfm

import com.example.data.DataApp
import com.example.lastfm.di.ApplicationComponent
import com.example.lastfm.di.DaggerApplicationComponent

class FMApplication : DataApp() {

    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent
            .factory()
            .create(this)
    }
}