package com.example.zennapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName(value = "article_type")
    val articleType: String,
    @SerialName(value = "body_letters_count")
    val bodyLettersCount: Int,
    @SerialName(value = "body_updated_at")
    val bodyUpdatedAt: String,
    @SerialName(value = "comments_count")
    val commentsCount: Int,
    val emoji: String,
    val id: Int,
    @SerialName(value = "is_suspending_private")
    val isSuspendingPrivate: Boolean,
    @SerialName(value = "liked_count")
    val likedCount: Int,
    val path: String,
    val pinned: Boolean,
    @SerialName(value = "post_type")
    val postType: String,
    val publication: Publication?,
    @SerialName(value = "published_at")
    val publishedAt: String,
    val slug: String,
    @SerialName(value = "source_repo_updated_at")
    val sourceRepoUpdatedAt: String? = null,
    val title: String,
    val user: User
)