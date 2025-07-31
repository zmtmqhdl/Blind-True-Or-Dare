package com.example.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.main.MainRoute
import com.example.presentation.gameRoom.GameRoomRoute
import com.example.presentation.resultRoom.ResultRoomRoute
import com.example.presentation.waitingRoom.WaitingRoomRoute

fun NavGraphBuilder.main(navController: NavController) {
    composable(route = Route.Main.route) {
        MainRoute(
            navigateToWaitingRoom = { navController.navigate(route = Route.WaitingRoom.route) }
        )
    }


    composable(route = Route.WaitingRoom.route) {
        WaitingRoomRoute(
            navigateToGameRoom = { navController.navigate(route = Route.GameRoom.route) },
            popBackStack = { navController.popBackStack() }
        )
    }

    composable(route = Route.GameRoom.route) {
        GameRoomRoute(
            navigateToResultRoom = { navController.navigate(route = Route.ResultRoom.route)},
            popBackStack = { navController.popBackStack() }
        )
    }

    composable(route = Route.ResultRoom.route) {
        ResultRoomRoute(
            popBackStack = { navController.popBackStack() }
        )
    }
}