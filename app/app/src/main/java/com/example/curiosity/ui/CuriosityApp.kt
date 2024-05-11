package com.example.curiosity.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.curiosity.R
import com.example.curiosity.ui.screens.AppViewModel
import com.example.curiosity.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuriosityApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar(

            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Sharp.Search, contentDescription = "Search content")
                    }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Sharp.Home, contentDescription = "Home screen")
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(imageVector = Icons.Sharp.Favorite, contentDescription = "Archived article")
                    }
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val appViewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
            HomeScreen(
                appUiState = appViewModel.appUiState,
                retryAction = appViewModel::getArticles,
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}