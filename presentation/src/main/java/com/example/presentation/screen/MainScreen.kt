package com.example.presentation.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.presentation.Icon.Close
import com.example.presentation.component.MyWorldSnackBar
import com.example.presentation.component.PrimaryNavigationBar
import com.example.presentation.component.PrimaryTopBar
import com.example.presentation.navigation.FirstGraph
import com.example.presentation.navigation.Screen
import com.example.presentation.navigation.SecondGraph

@Composable
fun MainScreen(navController: NavHostController) {

    val snackBarHostState = remember { SnackbarHostState() }
    var selectedTab by remember { mutableIntStateOf(0) }

    MyWorldScreen.PrimaryScaffold(
        topBar = {
            PrimaryTopBar(
                title = { Text("test") },
                leftIcons = listOf(Close),
                rightIcons = listOf(Close),
                onLeftIconClick = { },
                onRightIconClick = listOf(),
            )
        },
        bottomBar = {
            PrimaryNavigationBar(
                route = listOf(Screen.Main, Screen.Second),
                currentTab = selectedTab,
                onSelectedTab = { index -> selectedTab = index }
            )
        },
        snackBarHost = {
            MyWorldSnackBar(
                snackBarHostState = snackBarHostState
            )
        },
        content = {
            when (selectedTab) {
                0 -> {
                    FirstGraph(
                        navController = navController
                    )
                }

                1 -> {
                    SecondGraph(
                        navController = navController
                    )
                }
            }
        }
    )
}