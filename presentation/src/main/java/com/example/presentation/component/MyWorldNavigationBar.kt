package com.example.presentation.component

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.navigation.Route
import com.example.presentation.util.MyWorldPreview

@Composable
fun PrimaryNavigationBar(
    route: List<Route>,
    currentTab: Int,
    onSelectedTab: (Int) -> Unit
) {
    NavigationBar {
        route.forEachIndexed { index, value ->
            NavigationBarItem(
                selected = currentTab == index,
                onClick = {
                    onSelectedTab(index)
                },
                icon = {
                    MyWorldIcon(
                        icon = if (currentTab == index) value.selectedIcon else value.unselectedIcon
                    )
                },
                label = { value.label?.let { Text(it) } },
            )
        }
    }
}

@MyWorldPreview
@Composable
private fun PrimaryNavigationBarPreview() {
    PrimaryNavigationBar(
        route = listOf(Route.First, Route.Second),
        currentTab = 0,
        onSelectedTab = { index -> index }
    )
}