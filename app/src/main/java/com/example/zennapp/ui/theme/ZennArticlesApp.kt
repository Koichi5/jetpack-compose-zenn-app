package com.example.zennapp.ui.theme

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.zennapp.ui.theme.screens.ZennAppScaffold

@Composable
fun ZennArticlesApp() {
    ZennAppScaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZennTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Zenn Articles",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier
    )
}
