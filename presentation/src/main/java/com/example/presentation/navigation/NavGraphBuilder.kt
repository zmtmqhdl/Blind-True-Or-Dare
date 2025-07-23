package com.example.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.main.MainRoute
import com.example.presentation.questionRoom.QuestionRoomRoute
import com.example.presentation.waitingRoom.WaitingRoomRoute

fun NavGraphBuilder.main(navController: NavController) {
    composable(route = Route.Main.route) {
        MainRoute(
            navigateToWaitingRoom = { navController.navigate(route = Route.WaitingRoom.route) }
        )
    }


    composable(route = Route.WaitingRoom.route) {
        WaitingRoomRoute(
            navigateToQuestionRoom = { navController.navigate(route = Route.QuestionRoom.route) },
            popBackStack = { navController.popBackStack() }
        )
    }

    composable(route = Route.QuestionRoom.route) {
        QuestionRoomRoute(
            popBackStack = { navController.popBackStack() }
        )
    }
}