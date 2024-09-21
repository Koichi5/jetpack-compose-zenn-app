package com.example.zennapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.zennapp.ui.theme.ZennAppTheme
import com.example.zennapp.ui.theme.ZennArticlesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZennAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ZennArticlesApp()
                }
            }
        }
    }
}
