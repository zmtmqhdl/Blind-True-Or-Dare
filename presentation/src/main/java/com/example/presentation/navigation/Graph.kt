package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Route.Main.route
    ) {
        main(navController = navController)
    }
}

fun NavController.popCurrentAndNavigate(route: String) {
    val currentRoute = this.currentDestination?.route
    this.navigate(route = route) {
        currentRoute?.let { popUpTo(it) { inclusive = true } }
    }
}
