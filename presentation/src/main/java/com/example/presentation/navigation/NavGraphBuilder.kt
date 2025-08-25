package com.example.presentation.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
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
        LaunchedEffect(navController) {
            navController.currentBackStackEntryFlow.collect { entry ->
                Log.d("NavDebug", "=== Navigation Debug ===")
                Log.d("NavDebug", "Current: ${entry.destination.route}")
                Log.d("NavDebug", "Previous: ${navController.previousBackStackEntry?.destination?.route}")
                Log.d("NavDebug", "Can pop back: ${navController.previousBackStackEntry != null}")
                Log.d("NavDebug", "========================")
            }
        }

        WaitingRoomRoute(
            navigateToGameRoom = {
                navController.navigate(Route.GameRoom.route) {
                    popUpTo(Route.WaitingRoom.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
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