package com.ahmetroid.worldclocks.app

import android.content.Context
import com.ahmetroid.worldclocks.data.Repository
import com.ahmetroid.worldclocks.db.AppDatabase
import com.ahmetroid.worldclocks.network.Api

object ServiceLocator {

    @Volatile
    var repository: Repository? = null

    fun provideClocksRepository(context: Context): Repository {
        synchronized(this) {
            return repository ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context): Repository {
        val newRepository = Repository(Api.service, AppDatabase.getInstance(context))
        repository = newRepository
        return newRepository
    }

}