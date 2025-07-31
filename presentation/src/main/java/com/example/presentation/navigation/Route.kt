package com.example.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.core.Icon.Close

sealed class Route(
    val route: String,
    val label: String? = null,
    val selectedIcon: ImageVector = Close,
    val unselectedIcon: ImageVector = Close,
) {
    data object Splash : Route(
        route = "splash"
    )

    data object Main : Route(
        route = "main",
    )

    data object WaitingRoom: Route(
        route = "waiting_room",
    )

    data object GameRoom: Route(
        route = "game_room"
    )

    data object ResultRoom: Route(
        route = "result_room"
    )
}