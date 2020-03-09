package com.ahmetroid.worldclocks.app

import com.ahmetroid.worldclocks.data.ClocksRepository

object ServiceLocator {

    @Volatile
    var repository: ClocksRepository? = null

    fun provideClocksRepository(): ClocksRepository {
        synchronized(this) {
            return repository ?: createClocksRepository()
        }
    }

    private fun createClocksRepository(): ClocksRepository {
        val newRepository = ClocksRepository()
        repository = newRepository
        return newRepository
    }

}