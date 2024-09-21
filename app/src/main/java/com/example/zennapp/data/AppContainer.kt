package com.example.zennapp.data

import com.example.zennapp.network.ZennApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val zennArticlesRepository: ZennArticlesRepository
}

class DefaultAppContainer : AppContainer {
    private val username = "koichi_51"
    private val baseUrl = "https://zenn.dev/api/"

    private val contentType = "application/json".toMediaType()
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    private val retrofitService: ZennApiService by lazy {
        retrofit.create(ZennApiService::class.java)
    }

    override val zennArticlesRepository: ZennArticlesRepository by lazy {
        NetworkZennArticlesRepository(retrofitService, username)
    }
}