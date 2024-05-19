package com.example.curiosity.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.curiosity.model.Article

@Composable
fun SearchScreen(
    searchUiState: SearchUiState,
    onGeneratePrompt: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(0.88f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (searchUiState) {
                is SearchUiState.Loading -> {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Loading...")
                        Spacer(modifier = Modifier.width(4.dp))
                        CircularProgressIndicator()
                    }
                }
                is SearchUiState.Error -> Text(text = "Failed to load: $searchUiState.message")
                is SearchUiState.Success -> {
                    Text(
                        text = searchUiState.promptInput,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    Text(
                        text = searchUiState.promptResponse,
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())

                    )
                }
                else -> {
                    Text(text = "Search for something!")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search for articles or anything really!") },
            trailingIcon = {
                IconButton(onClick = {
                    onGeneratePrompt(searchText)
                    focusManager.clearFocus()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                }
            }
        )
    }
}
