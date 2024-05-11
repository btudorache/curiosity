package com.example.curiosity.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.curiosity.ui.ErrorScreen
import com.example.curiosity.ui.LoadingScreen

@Composable
fun ArchivedScreen(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
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
                contentPadding = contentPadding
            )
        is HomeUiState.Error -> ErrorScreen(retryAction, modifier, homeUiState.errorText)
    }
}
