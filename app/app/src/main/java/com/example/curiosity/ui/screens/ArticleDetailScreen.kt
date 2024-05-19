package com.example.curiosity.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.curiosity.model.Article

@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    article: Article,
) {
    Column(modifier = modifier) {
        ArticleCard(article = article, isFocused = true)
    }
}