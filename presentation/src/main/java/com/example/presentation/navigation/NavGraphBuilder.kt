package com.example.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.presentation.content.main.MainRoute
import com.example.presentation.content.gameRoom.GameRoomRoute
import com.example.presentation.content.scanQrCode.ScanQrCodeRoute
import com.example.presentation.content.resultRoom.ResultRoomRoute
import com.example.presentation.content.waitingRoom.WaitingRoomRoute

fun NavGraphBuilder.main(navController: NavController) {
    slideComposable(route = Route.Main.route) {
        MainRoute(
            navigateToQrCodeScan = {navController.navigate(Route.QrCodeScan.route)},
            navigateToWaitingRoom = {navController.navigate(Route.WaitingRoom.route)}
        )
    }

    slideComposable(route = Route.QrCodeScan.route) {
        ScanQrCodeRoute(
            popBackStack = { navController.popBackStack() },
            navigateToWaitingRoom = {navController.popNavigate(Route.WaitingRoom.route)}
        )
    }


    slideComposable(route = Route.WaitingRoom.route) {
        WaitingRoomRoute(
            navigateToGameRoom = {
                navController.popNavigate(Route.GameRoom.route)
            },
            popBackStack = { navController.popBackStack() }
        )
    }

    slideComposable(route = Route.GameRoom.route) {
        GameRoomRoute(
            navigateToResultRoom = {
                navController.popNavigate(route = Route.ResultRoom.route)
            },
            popBackStack = { navController.popBackStack() }
        )
    }

    slideComposable(route = Route.ResultRoom.route) {
        ResultRoomRoute(
            navigateToWaitingRoom = { navController.popNavigate(route = Route.WaitingRoom.route) },
            popBackStack = { navController.popBackStack() }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.slideComposable(
    route: String,
    duration: Int = 500,
    content: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route = route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(duration, easing = FastOutSlowInEasing)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(duration, easing = FastOutSlowInEasing)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(duration, easing = FastOutSlowInEasing)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(duration, easing = FastOutSlowInEasing)
            )
        },
        content = content
    )
}