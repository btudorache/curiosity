package com.example.curiosity.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.curiosity.R
import com.example.curiosity.model.Article
import com.example.curiosity.ui.screens.AppViewModel
import com.example.curiosity.ui.screens.ArchivedScreen
import com.example.curiosity.ui.screens.ArticleDetailScreen
import com.example.curiosity.ui.screens.HomeScreen
import com.example.curiosity.ui.screens.LoginScreen
import com.example.curiosity.ui.screens.ProfileScreen
import com.example.curiosity.ui.screens.RegisterScreen
import com.example.curiosity.ui.screens.RegisterUiState
import com.example.curiosity.ui.screens.SearchScreen

enum class CuriosityScreen() {
    Login,
    Register,
    Home,
    Search,
    Archived,
    Article,
    Profile
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuriosityApp() {
    val navController: NavHostController = rememberNavController()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = CuriosityScreen.valueOf(
        backStackEntry?.destination?.route ?: CuriosityScreen.Login.name
    )

    val appViewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)

    val onFocusArticle = { article: Article ->
        appViewModel.setFocusedArticle(article)
        navController.navigate(CuriosityScreen.Article.name)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(CuriosityScreen.Profile.name)
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.AccountCircle,
                            contentDescription = "Account page"
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (appViewModel.isAuthenticated) {
                NavigationBar(

                ) {
                    NavigationBarItem(
                        selected = currentScreen == CuriosityScreen.Search,
                        onClick = {
                            navController.navigate(CuriosityScreen.Search.name)
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Sharp.Search,
                                contentDescription = "Search content"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = currentScreen == CuriosityScreen.Home,
                        onClick = {
                            navController.navigate(CuriosityScreen.Home.name)
                        },
                        icon = {
                            Icon(imageVector = Icons.Sharp.Home, contentDescription = "Home screen")
                        }
                    )
                    NavigationBarItem(
                        selected = currentScreen == CuriosityScreen.Archived,
                        onClick = {
                            navController.navigate(CuriosityScreen.Archived.name)
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Sharp.Favorite,
                                contentDescription = "Archived article"
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = CuriosityScreen.Login.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(CuriosityScreen.Login.name) {
                    LoginScreen(
                        loginUiState = appViewModel.loginUiState,
                        onLogin = { username, password ->
                            appViewModel.loginUser(username, password)
                        },
                        onRegisterScreen = {
                            appViewModel.resetRegisterState()
                            navController.navigate(CuriosityScreen.Register.name)
                        },
                        onSuccessfulLogin = {
                            appViewModel.getArticles()
                            appViewModel.getUserArticles()
                            navController.navigate(CuriosityScreen.Home.name)
                        }
                    )
                }
                composable(CuriosityScreen.Register.name) {
                    RegisterScreen(
                        registerUiState = appViewModel.registerUiState,
                        onRegister = { username, password, email ->
                            appViewModel.registerUser(username, password, email)
                        },
                        onBack = {
                            navController.navigate(CuriosityScreen.Login.name)
                        }
                    )
                }
                composable(CuriosityScreen.Home.name) {
                    HomeScreen(
                        homeUiState = appViewModel.homeUiState,
                        retryAction = appViewModel::getArticles,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = innerPadding,
                        onFocusArticle = onFocusArticle
                    )
                }
                composable(CuriosityScreen.Search.name) {
                    SearchScreen(
                        searchUiState = appViewModel.searchUiState,
                        onGeneratePrompt = appViewModel::generateText
                    )
                }
                composable(CuriosityScreen.Archived.name) {
                    ArchivedScreen(
                        userArticles = appViewModel.userArticles,
                        retryAction = appViewModel::getArticles,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = innerPadding,
                        onFocusArticle = onFocusArticle
                    )
                }
                composable(CuriosityScreen.Article.name) {
                    ArticleDetailScreen(
                        article = appViewModel.focusArticle
                    )
                }
                composable(CuriosityScreen.Profile.name) {
                    ProfileScreen(
                        onLogout = {
                            appViewModel.resetUser()
                            navController.navigate(CuriosityScreen.Login.name)
                        },
                        username = appViewModel.username
                    )
                }
            }
        }
    }
}