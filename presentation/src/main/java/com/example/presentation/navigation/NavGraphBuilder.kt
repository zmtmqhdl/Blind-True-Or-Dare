package com.example.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.content.main.MainRoute
import com.example.presentation.content.gameRoom.GameRoomRoute
import com.example.presentation.content.resultRoom.ResultRoomRoute
import com.example.presentation.content.waitingRoom.WaitingRoomRoute

fun NavGraphBuilder.main(navController: NavController) {
    composable(route = Route.Main.route) {
        MainRoute(
            navigateToWaitingRoom = { navController.navigate(route = Route.WaitingRoom.route) }
        )
    }


    composable(route = Route.WaitingRoom.route) {
        WaitingRoomRoute(
            navigateToGameRoom = {
                navController.popNavigate(Route.GameRoom.route)
            },
            popBackStack = { navController.popBackStack() }
        )
    }

    composable(route = Route.GameRoom.route) {
        GameRoomRoute(
            navigateToResultRoom = {
                navController.popNavigate(route = Route.ResultRoom.route)
            },
            popBackStack = { navController.popBackStack() }
        )
    }

    composable(route = Route.ResultRoom.route) {
        ResultRoomRoute(
            navigateToWaitingRoom = { navController.popNavigate(route = Route.WaitingRoom.route) },
            popBackStack = { navController.popBackStack() }
        )
    }
}