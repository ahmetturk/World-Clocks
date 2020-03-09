package com.ahmetroid.worldclocks.app

import com.ahmetroid.worldclocks.data.Repository
import com.ahmetroid.worldclocks.network.Api

object ServiceLocator {

    @Volatile
    var repository: Repository? = null

    fun provideClocksRepository(): Repository {
        synchronized(this) {
            return repository ?: createRepository()
        }
    }

    private fun createRepository(): Repository {
        val newRepository = Repository(Api.service)
        repository = newRepository
        return newRepository
    }

}