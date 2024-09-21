package com.example.zennapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class User(
    @SerialName(value = "avatar_small_url")
    val avatarSmallUrl: String,
    val id: Int,
    val name: String,
    val username: String
)