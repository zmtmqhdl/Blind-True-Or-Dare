package com.example.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.screen.FirstScreen
import com.example.presentation.screen.SecondScreen

fun NavGraphBuilder.main(navController: NavController) {
    composable(Screen.Main.route) {
        com.example.presentation.main.MainScreen()
    }

    composable(Screen.WaitingRoom.route) {
        com.example.presentation.waitingRoom.MainScreen()
    }
}


fun NavGraphBuilder.firstGraph(navController: NavController) {
    composable(Screen.First.route) { FirstScreen() }
}

fun NavGraphBuilder.secondGraph(navController: NavController) {
    composable(Screen.Second.route) { SecondScreen() }
}