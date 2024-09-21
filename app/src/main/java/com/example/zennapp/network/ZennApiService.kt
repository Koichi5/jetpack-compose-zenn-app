package com.example.zennapp.network

import com.example.zennapp.model.ZennArticle
import retrofit2.http.GET
import retrofit2.http.Query

interface ZennApiService {
    @GET("articles")
    suspend fun getArticles(): ZennArticle

    @GET("articles")
    suspend fun getLatestArticles(
        @Query("order") order: String = "latest"
    ): ZennArticle

    @GET("articles")
    suspend fun getMyArticles(@Query("username") username: String): ZennArticle

    @GET("articles")
    suspend fun getMyLatestArticles(
        @Query("username") username: String, @Query("order") order: String = "latest"
    ): ZennArticle

    @GET("articles")
    suspend fun getTopicsArticles(@Query("topicname") topicName: String): ZennArticle

    @GET("articles")
    suspend fun getTopicsLatestArticles(
        @Query("topicname") topicName: String, @Query("order") order: String = "latest"
    ): ZennArticle
}