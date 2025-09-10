package com.example.presentation.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
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
                navController.navigate(Route.GameRoom.route) {
                    popUpTo(Route.WaitingRoom.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            popBackStack = { navController.popBackStack() }
        )
    }

    composable(route = Route.GameRoom.route) {
        GameRoomRoute(
            navigateToResultRoom = {
                navController.navigate(route = Route.ResultRoom.route) {
                    popUpTo(Route.GameRoom.route) {
                        inclusive = true
                    }
                }
            },
            popBackStack = { navController.popBackStack() }
        )
    }

    composable(route = Route.ResultRoom.route) {
        ResultRoomRoute(
            popBackStack = { navController.popBackStack() }
        )
    }
}