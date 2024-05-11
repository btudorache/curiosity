/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.curiosity.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.curiosity.CuriosityApplication
import com.example.curiosity.data.BackendRepository
import com.example.curiosity.model.Article
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface AppUiState {
    data class Success(val articles: List<Article>) : AppUiState
    data class Error(val errorText: String) : AppUiState
    object Loading : AppUiState
}


class AppViewModel(private val backendRepository: BackendRepository) : ViewModel() {

    var appUiState: AppUiState by mutableStateOf(AppUiState.Loading)
        private set

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            appUiState = AppUiState.Loading
            appUiState = try {
                AppUiState.Success(backendRepository.getAmphibians())
            } catch (e: IOException) {
                AppUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                AppUiState.Error(e.message.toString())
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CuriosityApplication)
                val amphibiansRepository = application.container.backendRepository
                AppViewModel(backendRepository = amphibiansRepository)
            }
        }
    }
}
