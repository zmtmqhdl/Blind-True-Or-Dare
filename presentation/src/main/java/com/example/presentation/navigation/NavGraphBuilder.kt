package com.example.presentation.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.main.MainRoute
import com.example.presentation.screen.FirstScreen
import com.example.presentation.screen.SecondScreen
import com.example.presentation.main.MainViewModel
import com.example.presentation.waitingRoom.WaitingRoomRoute

fun NavGraphBuilder.main(navController: NavController) {
    composable(route = Route.Main.route) {
        val parentEntry = remember(it) {
            navController.getBackStackEntry(Route.Main.route)
        }

        MainRoute(
            mainViewModel = hiltViewModel(parentEntry),
            navigateToWaitingRoom ={  navController.navigate(route = Route.WaitingRoom.route) }
        )
    }

    composable(route = Route.Join.route) {
        val parentEntry = remember(it) {
            navController.getBackStackEntry(Route.Main.route)
        }

        val mainViewModel: MainViewModel = hiltViewModel(parentEntry)


    }


    composable(route = Route.WaitingRoom.route) {
        WaitingRoomRoute()
    }
}


fun NavGraphBuilder.firstGraph(navController: NavController) {
    composable(Route.First.route) { FirstScreen() }
}

fun NavGraphBuilder.secondGraph(navController: NavController) {
    composable(Route.Second.route) { SecondScreen() }
}