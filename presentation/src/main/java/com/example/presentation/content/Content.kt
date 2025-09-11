package com.example.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectTheme
import com.example.presentation.navigation.MainGraph
import com.example.presentation.navigation.Route
import com.example.presentation.navigation.popCurrentAndNavigate
import kotlinx.coroutines.launch

@Composable
fun Content(

) {
    // view model
    val contentViewModel: ContentViewModel = hiltViewModel()

    // view model state value
    val loading by contentViewModel.loading.collectAsState()

    // local state
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        launch {
            contentViewModel.handleWebSocketConnect(
                onConnect = { navController.navigate(Route.WaitingRoom.route) },
                onDisconnect = { navController.popCurrentAndNavigate(Route.Main.route) }
            )
        }
    }

    ProjectTheme {
        ProjectScreen.LoadingScreen(
            loading = loading,
            loadingColor = ProjectTheme.color.white,
            content = {
                MainGraph(navController = navController)
            }
        )
    }
}