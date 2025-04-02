package com.example.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.presentation.Icon.Close

sealed class Screen(
    val route: String,
    val label: String? = null,
    val selectedIcon: ImageVector = Close,
    val unselectedIcon: ImageVector = Close
) {
    data object Splash : Screen(
        route = "splash"
    )

    data object Main : Screen(
        route = "main",
        label = "main",
        selectedIcon = Close,
        unselectedIcon = Close
    )

    data object First : Screen(
        route = "first",
        label = "first",
        selectedIcon = Close,
        unselectedIcon = Close
    )

    data object Second : Screen(
        route = "second",
        label = "second",
        selectedIcon = Close,
        unselectedIcon = Close
    )
}