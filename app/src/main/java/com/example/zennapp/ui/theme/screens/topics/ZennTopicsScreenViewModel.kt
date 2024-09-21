package com.example.zennapp.ui.theme.screens.topics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.zennapp.ZennArticlesApplication
import com.example.zennapp.data.ZennArticlesRepository
import com.example.zennapp.ui.theme.screens.home.ZennUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class ZennTopicsScreenViewModel(private val zennArticlesRepository: ZennArticlesRepository) : ViewModel() {
    var zennUiState: ZennUiState by mutableStateOf(ZennUiState.Loading)
        private set

    val topics = listOf(
        "jetpack", "flutter", "swift", "kotlin", "python", "javascript"
    )

    private var currentTopic = MutableStateFlow(topics.first())

    init {
        getTopicsLatestArticle(currentTopic.value)
    }

    fun getTopicsLatestArticle(topicName: String) {
        viewModelScope.launch {
            zennUiState = ZennUiState.Loading
            currentTopic.value = topicName
            zennUiState = try {
                ZennUiState.Success(zennArticlesRepository.getTopicsLatestArticle(topicName))
            } catch (e: IOException) {
                ZennUiState.Error
            } catch (e: HttpException) {
                ZennUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ZennArticlesApplication)
                val zennArticlesRepository = application.container.zennArticlesRepository
                ZennTopicsScreenViewModel(zennArticlesRepository = zennArticlesRepository)
            }
        }
    }
}