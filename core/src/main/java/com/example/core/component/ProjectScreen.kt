package com.example.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.core.theme.ProjectTheme

object ProjectScreen {
    @Composable
    fun Screen(
        containerColor: Color = ProjectTheme.color.background,
        loading: Boolean = false,
        loadingColor: Color = ProjectTheme.color.primary.background,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = ProjectTheme.space.space4,
                        end = ProjectTheme.space.space4
                    )
                    .padding(WindowInsets.systemBars.asPaddingValues())
                    .background(color = containerColor)
            ) {
                content()
            }

            if (loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ProjectTheme.color.black.copy(alpha = 0.3f))
                        .clickable(enabled = false) {},
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(ProjectTheme.space.space12),
                        color = loadingColor,
                        strokeWidth = ProjectTheme.space.space1
                    )
                }
            }
        }
    }

    @Composable
    fun Scaffold(
        topBar: @Composable () -> Unit,
        bottomBar: @Composable () -> Unit,
        snackBarHost: @Composable () -> Unit,
        containerColor: Color = ProjectTheme.color.background,
        content: @Composable () -> Unit
    ) {
        Scaffold(
            topBar = topBar,
            bottomBar = bottomBar,
            snackbarHost = snackBarHost,
            containerColor = containerColor
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
                    .padding(
                        start = ProjectTheme.space.space4,
                        end = ProjectTheme.space.space4
                    )
            ) {
                content()
            }
        }
    }

    @Composable
    fun LoadingScreen(
        containerColor: Color = ProjectTheme.color.background,
        loading: Boolean,
        loadingColor: Color = ProjectTheme.color.primary.background,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(color = containerColor)
        ) {
            content()

            if (loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ProjectTheme.color.black.copy(alpha = 0.3f))
                        .clickable(enabled = false) {},
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(ProjectTheme.space.space12),
                        color = loadingColor,
                        strokeWidth = ProjectTheme.space.space1
                    )
                }
            }
        }
    }
}