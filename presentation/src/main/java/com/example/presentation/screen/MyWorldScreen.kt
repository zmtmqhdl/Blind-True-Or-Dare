package com.example.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.presentation.theme.MyWorldTheme

object MyWorldScreen {
    @Composable
    fun PrimaryScreen(
        color: Color = MyWorldTheme.color.white,
        content: @Composable () -> Unit
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = MyWorldTheme.space.space4,
                    end = MyWorldTheme.space.space4
                )
                .padding(WindowInsets.systemBars.asPaddingValues())
                .background(color = color)
        ) {
            content()
        }
    }

    @Composable
    fun PrimaryScaffold(
        topBar: @Composable () -> Unit,
        bottomBar: @Composable () -> Unit,
        snackBarHost: @Composable () -> Unit,
        containerColor: Color = MyWorldTheme.color.white,
        content: @Composable () -> Unit
    ) {
        Scaffold(
            modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = snackBarHost,
            containerColor = containerColor
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(
                        start = MyWorldTheme.space.space4,
                        end = MyWorldTheme.space.space4
                    )
            ) {
                content()
            }
        }
    }
}
