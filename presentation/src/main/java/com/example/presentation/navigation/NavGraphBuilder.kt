package com.example.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.main.MainRoute
import com.example.presentation.screen.FirstScreen
import com.example.presentation.screen.SecondScreen
import com.example.presentation.waitingRoom.WaitingRoomRoute

fun NavGraphBuilder.main(navController: NavController) {
    composable(Route.Main.route) {
        MainRoute()
    }

    composable(Route.WaitingRoom.route) {
        WaitingRoomRoute()
    }
}


fun NavGraphBuilder.firstGraph(navController: NavController) {
    composable(Route.First.route) { FirstScreen() }
}

fun NavGraphBuilder.secondGraph(navController: NavController) {
    composable(Route.Second.route) { SecondScreen() }
}