package com.example.curiosity.network

import kotlinx.serialization.Serializable

@Serializable
data class RegisterData(
    val username: String,
    val password: String,
    val email: String
)
