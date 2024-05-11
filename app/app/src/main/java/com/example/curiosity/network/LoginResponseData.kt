package com.example.curiosity.network

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseData(
    val token: String
)
