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
import com.example.curiosity.network.LoginData
import com.example.curiosity.network.LoginResponseData
import com.example.curiosity.network.RegisterData
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface HomeUiState {
    data class Success(val articles: List<Article>) : HomeUiState
    data class Error(val errorText: String) : HomeUiState
    object Loading : HomeUiState
}

sealed interface RegisterUiState {
    object Idle : RegisterUiState
    object Success : RegisterUiState
    data class Error(val errorText: String) : RegisterUiState
    object Loading : RegisterUiState
}

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Success : LoginUiState
    data class Error(val errorText: String) : LoginUiState
    object Loading : LoginUiState
}


class AppViewModel(private val backendRepository: BackendRepository) : ViewModel() {

    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    var registerUiState: RegisterUiState by mutableStateOf(RegisterUiState.Idle)
        private set

    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Idle)
        private set

    var isAuthenticated by mutableStateOf(false)
    var authenticationToken by mutableStateOf("")

//    init {
//        getArticles()
//    }

    fun getArticles() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            homeUiState = try {
                HomeUiState.Success(backendRepository.getArticles())
            } catch (e: IOException) {
                HomeUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                HomeUiState.Error(e.message.toString())
            }
        }
    }

    fun registerUser(username: String, password: String, email: String) {
        viewModelScope.launch {
            registerUiState = RegisterUiState.Loading
            registerUiState = try {
                backendRepository.registerUser(RegisterData(username, password, email))
                RegisterUiState.Success
            } catch (e: IOException) {
                RegisterUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                RegisterUiState.Error(e.message.toString())
            }
        }
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            loginUiState = LoginUiState.Loading
            try {
                backendRepository.loginUser(LoginData(username, password))
                val loginResult = backendRepository.loginUser(LoginData(username, password))
                isAuthenticated = true
                authenticationToken = loginResult.token
                loginUiState = LoginUiState.Success
            } catch (e: IOException) {
                loginUiState = LoginUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                loginUiState = LoginUiState.Error(e.message.toString())
            }
        }
    }

    fun resetUser() {
        loginUiState = LoginUiState.Idle
        registerUiState = RegisterUiState.Idle
        isAuthenticated = false
        authenticationToken = ""
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CuriosityApplication)
                val backendRepository = application.container.backendRepository
                AppViewModel(backendRepository = backendRepository)
            }
        }
    }
}
