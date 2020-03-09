package com.ahmetroid.worldclocks.network

import com.ahmetroid.worldclocks.data.model.Response
import retrofit2.http.GET

interface ApiService {

    @GET("response")
    suspend fun getResponse(): Response

}