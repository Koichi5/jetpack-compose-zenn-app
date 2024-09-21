package com.example.zennapp.ui.theme.screens.mypage

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
import kotlinx.coroutines.launch
import java.io.IOException

class ZennMyPageScreenViewModel(private val zennArticlesRepository: ZennArticlesRepository): ViewModel() {
    var zennUiState: ZennUiState by mutableStateOf(ZennUiState.Loading)

    init {
        getMyArticles()
    }

    fun getMyArticles() {
        viewModelScope.launch {
            zennUiState = ZennUiState.Loading
            zennUiState = try {
                ZennUiState.Success(zennArticlesRepository.getMyArticles())
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
                ZennUiState.Success(zennArticlesRepository.getMyLatestArticles())
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
                ZennMyPageScreenViewModel(zennArticlesRepository = zennArticlesRepository)
            }
        }
    }
}