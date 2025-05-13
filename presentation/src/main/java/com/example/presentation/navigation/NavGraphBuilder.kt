package com.example.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.presentation.screen.FirstScreen
import com.example.presentation.main.MainScreen
import com.example.presentation.screen.SecondScreen
import com.example.presentation.waitingRoom.WaitingRoomScreen

fun NavGraphBuilder.main() {
    composable(Screen.Main.route) {
        MainScreen()
    }

    composable(Screen.WaitingRoom.route) {
        WaitingRoomScreen()
    }
}


fun NavGraphBuilder.firstGraph(navController: NavController) {
    composable(Screen.First.route) { FirstScreen() }
}

fun NavGraphBuilder.secondGraph(navController: NavController) {
    composable(Screen.Second.route) { SecondScreen() }
}