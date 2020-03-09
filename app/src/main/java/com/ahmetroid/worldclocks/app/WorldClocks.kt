package com.ahmetroid.worldclocks.app

import android.app.Application
import com.ahmetroid.worldclocks.data.ClocksRepository

class WorldClocks : Application() {

    val repository: ClocksRepository
        get() = ServiceLocator.provideClocksRepository()

}