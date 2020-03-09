package com.ahmetroid.worldclocks.app

import android.app.Application
import com.ahmetroid.worldclocks.BuildConfig
import com.ahmetroid.worldclocks.data.Repository
import timber.log.Timber
import timber.log.Timber.DebugTree

class WorldClocks : Application() {

    val repository: Repository
        get() = ServiceLocator.provideClocksRepository()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}