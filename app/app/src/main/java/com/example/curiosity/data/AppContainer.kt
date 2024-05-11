package com.example.curiosity.data

import com.example.curiosity.network.ServerApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val backendRepository: BackendRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "http://192.168.50.11:8082"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: ServerApiService by lazy {
        retrofit.create(ServerApiService::class.java)
    }

    override val backendRepository: BackendRepository by lazy {
        DefaultBackendRepository(retrofitService)
    }
}
