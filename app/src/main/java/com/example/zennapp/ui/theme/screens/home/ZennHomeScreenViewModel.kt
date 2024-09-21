package com.example.zennapp.ui.theme.screens.home

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
import com.example.zennapp.model.ZennArticle
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ZennUiState {
    data class Success(val articles: ZennArticle) : ZennUiState
    data object Error: ZennUiState
    data object Loading: ZennUiState
}

class ZennHomeScreenViewModel(private val zennArticlesRepository: ZennArticlesRepository): ViewModel() {
    var zennUiState: ZennUiState by mutableStateOf(ZennUiState.Loading)
        private set

    init {
        getLatestArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            zennUiState = ZennUiState.Loading
            zennUiState = try {
                ZennUiState.Success(zennArticlesRepository.getArticles())
            } catch (e: IOException) {
                ZennUiState.Error
            } catch (e: HttpException) {
                ZennUiState.Error
            }
        }
    }

    fun getLatestArticles() {
        viewModelScope.launch {
            zennUiState = ZennUiState.Loading
            zennUiState = try {
                ZennUiState.Success(zennArticlesRepository.getLatestArticles())
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
                ZennHomeScreenViewModel(zennArticlesRepository = zennArticlesRepository)
            }
        }
    }
}
