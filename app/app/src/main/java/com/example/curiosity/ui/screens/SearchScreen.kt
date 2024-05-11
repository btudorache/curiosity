package com.example.curiosity.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.curiosity.model.Article

@Composable
fun SearchScreen(
    searchText: String,
    onSearchTextChanged: (String) -> Unit
) {
    // TODO use gemini model to search and generate result
    val searchResults: List<Article> = emptyList()

    Column(modifier = Modifier.fillMaxSize()) {
        // Search bar
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Search results
        if (searchResults.isNotEmpty()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    ArticleCard(article = searchResults[0])
                }
            }
        } else {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(text = "No results found")
            }
        }
    }
}
