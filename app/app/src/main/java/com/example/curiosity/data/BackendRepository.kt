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

package com.example.curiosity.data


import com.example.curiosity.model.Article
import com.example.curiosity.network.LoginData
import com.example.curiosity.network.LoginResponseData
import com.example.curiosity.network.RegisterData
import com.example.curiosity.network.ServerApiService


interface BackendRepository {
    suspend fun getArticles(): List<Article>

    suspend fun registerUser(registerData: RegisterData)

    suspend fun loginUser(loginData: LoginData): LoginResponseData
}


class DefaultBackendRepository(
    private val serverApiService: ServerApiService
) : BackendRepository {
    override suspend fun getArticles(): List<Article> = serverApiService.getArticles()

    override suspend fun registerUser(registerData: RegisterData) = serverApiService.registerUser(registerData)

    override suspend fun loginUser(loginData: LoginData) = serverApiService.loginUser(loginData)
}
