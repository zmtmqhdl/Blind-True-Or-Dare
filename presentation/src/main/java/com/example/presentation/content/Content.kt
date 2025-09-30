package com.example.presentation.content

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectTheme
import com.example.presentation.navigation.MainGraph

import kotlinx.coroutines.launch

@SuppressLint("RestrictedApi")
@Composable
fun Content() {
    // view model
    val viewModel: ContentViewModel = hiltViewModel()

    // view model state value
    val loading by viewModel.loading.collectAsState()
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsState()

    // local state
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        launch {
            viewModel.handleWebSocketConnect(
                onDisconnect = { navController.popBackStack() }
            )
        }
    }

    ProjectScreen.LoadingScreen(
        loading = loading,
        loadingColor = ProjectTheme.color.white,
        content = {
            MainGraph(navController = navController)
        }
    )


}