package com.bmk.data

import com.bmk.data.data.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("android_assignment")
    suspend fun getData(): Response<DataResponse>
}

