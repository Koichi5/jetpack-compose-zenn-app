package com.example.zennapp.ui.theme.screens.topics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.zennapp.ui.theme.screens.ArticlesScreen
import com.example.zennapp.ui.theme.screens.DropdownMenuItemData
import com.example.zennapp.ui.theme.screens.ErrorScreen
import com.example.zennapp.ui.theme.screens.LoadingScreen
import com.example.zennapp.ui.theme.screens.home.ZennUiState
import java.util.Locale

@Composable
fun TopicsScreen(
    viewModel: ZennTopicsScreenViewModel,
    modifier: Modifier = Modifier
) {
    when (val zennUiState = viewModel.zennUiState) {
        is ZennUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ZennUiState.Success -> ArticlesScreen(
            zennUiState.articles,
            modifier = modifier.fillMaxWidth(),
            menuItems = viewModel.topics.map { topic ->
                DropdownMenuItemData(
                    label = topic.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    onClick = { viewModel.getTopicsLatestArticle(topic) }
                )
            }
        )

        is ZennUiState.Error -> ErrorScreen(
            retryAction = { viewModel.getTopicsLatestArticle(viewModel.topics.first()) },
            modifier = modifier.fillMaxSize()
        )
    }
}