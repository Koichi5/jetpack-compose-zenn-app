package com.example.zennapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZennArticle(
    val articles: List<Article>,
    @SerialName(value = "next_page")
    val nextPage: Int?
)