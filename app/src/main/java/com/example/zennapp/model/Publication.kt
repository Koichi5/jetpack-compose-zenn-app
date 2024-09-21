package com.example.zennapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Publication(
    val id: Int,
    val name: String,
    @SerialName("display_name")
    val displayName: String,
    @SerialName("avatar_small_url")
    val avatarSmallUrl: String,
    val pro: Boolean,
    @SerialName("avatar_registered")
    val avatarRegistered: Boolean
)