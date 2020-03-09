package com.ahmetroid.worldclocks.data

import com.ahmetroid.worldclocks.data.model.Response
import com.ahmetroid.worldclocks.network.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getResponse(): Response {
        return apiService.getResponse()
    }

}