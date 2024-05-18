package com.example.curiosity.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleInput(
    val title: String,
    val content: String
)
