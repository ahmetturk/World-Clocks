package com.ahmetroid.worldclocks.data

import androidx.lifecycle.LiveData
import com.ahmetroid.worldclocks.data.model.Clock
import com.ahmetroid.worldclocks.data.model.Response
import com.ahmetroid.worldclocks.db.AppDatabase
import com.ahmetroid.worldclocks.network.ApiService

class Repository(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) {

    suspend fun getResponse(): Response {
        return apiService.getResponse()
    }

    suspend fun insertToDb(clock: Clock) {
        appDatabase.clocksDao().insert(clock)
    }

    suspend fun deleteFromDb(clock: Clock) {
        appDatabase.clocksDao().delete(clock)
    }

    fun getClocks(): LiveData<List<Clock>> {
        return appDatabase.clocksDao().getAll()
    }

}