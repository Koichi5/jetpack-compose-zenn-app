package com.example.zennapp.ui.theme.screens.mypage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.zennapp.ui.theme.screens.ArticlesScreen
import com.example.zennapp.ui.theme.screens.DropdownMenuItemData
import com.example.zennapp.ui.theme.screens.ErrorScreen
import com.example.zennapp.ui.theme.screens.LoadingScreen
import com.example.zennapp.ui.theme.screens.home.ZennUiState

@Composable
fun MyPageScreen(
    zennUiState: ZennUiState,
    retryAction: () -> Unit,
    getArticles: () -> Unit,
    getLatestArticles: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (zennUiState) {
        is ZennUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ZennUiState.Success -> ArticlesScreen(
            zennUiState.articles,
            modifier = modifier.fillMaxWidth(),
            menuItems = listOf(
                DropdownMenuItemData(
                    label = "指定なし",
                    onClick = getArticles
                ),
                DropdownMenuItemData(
                    label = "新しい投稿順",
                    onClick = getLatestArticles
                )
            ),
        )

        is ZennUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}