package com.example.zennapp

import android.app.Application
import com.example.zennapp.data.AppContainer
import com.example.zennapp.data.DefaultAppContainer
import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
class ZennArticlesApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}