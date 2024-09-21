package com.example.zennapp.data

import com.example.zennapp.model.ZennArticle
import com.example.zennapp.network.ZennApiService

interface ZennArticlesRepository {
    suspend fun getArticles(): ZennArticle
    suspend fun getLatestArticles(): ZennArticle
    suspend fun getMyArticles(): ZennArticle
    suspend fun getMyLatestArticles(): ZennArticle
    suspend fun getTopicsArticle(topicName: String): ZennArticle
    suspend fun getTopicsLatestArticle(topicName: String): ZennArticle
}

class NetworkZennArticlesRepository(
    private val zennApiService: ZennApiService,
    private val username: String
) : ZennArticlesRepository {
    override suspend fun getArticles(): ZennArticle = zennApiService.getArticles()
    override suspend fun getLatestArticles(): ZennArticle =
        zennApiService.getLatestArticles()

    override suspend fun getMyArticles(): ZennArticle =
        zennApiService.getMyArticles(username)

    override suspend fun getMyLatestArticles(): ZennArticle =
        zennApiService.getMyLatestArticles(username)

    override suspend fun getTopicsArticle(topicName: String): ZennArticle =
        zennApiService.getTopicsArticles(topicName = topicName)

    override suspend fun getTopicsLatestArticle(topicName: String): ZennArticle =
        zennApiService.getTopicsLatestArticles(topicName = topicName)
}