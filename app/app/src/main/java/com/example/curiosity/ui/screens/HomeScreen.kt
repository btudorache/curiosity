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

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.curiosity.model.Article
import com.example.curiosity.ui.ErrorScreen
import com.example.curiosity.ui.LoadingScreen

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onFocusArticle: (Article) -> Unit
) {
    when (homeUiState) {
        is HomeUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        is HomeUiState.Success ->
            ArticlesListScreen(
                articles = homeUiState.articles,
                modifier = modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    ),
                contentPadding = contentPadding,
                onFocusArticle = onFocusArticle
            )
        is HomeUiState.Error -> ErrorScreen(retryAction, modifier, homeUiState.errorText)
        else -> {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(article: Article, modifier: Modifier = Modifier, onFocusArticle: (Article) -> Unit = {}, isFocused: Boolean = false) {
    val articleContent = if (isFocused) {
        article.content
    } else {
        if (article.content.length > 200) {
            "${article.content.substring(0, 200)}..."
        } else {
            article.content
        }
    }
    val onFocusArticleHandler: (Article) -> Unit = if (!isFocused) onFocusArticle else { _ -> }
    val cardModifier = if (isFocused) modifier.padding(16.dp) else modifier
    val textModifier = if (isFocused) Modifier.padding(16.dp).verticalScroll(rememberScrollState()) else Modifier.padding(16.dp)

    Card(
        modifier = cardModifier,
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onFocusArticleHandler(article)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
//            AsyncImage(
//                modifier = Modifier.fillMaxWidth(),
//                model = ImageRequest.Builder(context = LocalContext.current)
//                    .data(article.imgSrc)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = null,
//                contentScale = ContentScale.FillWidth,
//            )
            Text(
                text = articleContent,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify,
                modifier = textModifier
            )
        }
    }
}

@Composable
fun ArticlesListScreen(
    articles: List<Article>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onFocusArticle: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = articles,
            key = { article ->
                article.id
            }
        ) { amphibian ->
            ArticleCard(article = amphibian, modifier = Modifier.fillMaxSize(), onFocusArticle = onFocusArticle)
        }
    }
}

