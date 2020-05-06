package com.example.data

import android.app.Application
import androidx.room.Room
import com.example.data.local.database.LastDatabase

open class DataApp : Application() {

    lateinit var database: LastDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            LastDatabase::class.java,
            "LAstFM"
        ).fallbackToDestructiveMigration()
            .build()
    }
}