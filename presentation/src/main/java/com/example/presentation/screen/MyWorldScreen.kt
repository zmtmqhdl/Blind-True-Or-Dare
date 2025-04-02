package com.example.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.theme.MyWorldTheme

object MyWorldScreen {
    @Composable
    fun PrimaryScreen(
        content: @Composable () -> Unit
    ) {
        Column(
            modifier = Modifier.padding(
                start = MyWorldTheme.space.space4,
                end = MyWorldTheme.space.space4
            )
        ) {
            content()
        }
    }

    @Composable
    fun PrimaryScaffold(
        topBar: @Composable () -> Unit,
        bottomBar: @Composable () -> Unit,
        snackBarHost: @Composable () -> Unit,
        content: @Composable () -> Unit
    ) {
        Scaffold(
            modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = snackBarHost
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
