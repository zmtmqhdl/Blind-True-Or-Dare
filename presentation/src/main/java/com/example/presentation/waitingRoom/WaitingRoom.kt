package com.example.presentation.waitingRoom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.presentation.R
import com.example.presentation.screen.ProjectScreen
import com.example.presentation.theme.ProjectTheme

@Composable
fun WaitingRoomRoute() {
    WaitingRoomScreen()
}

@Composable
fun WaitingRoomScreen() {
    ProjectScreen.PrimaryScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = ProjectTheme.color.white,
                    shape = ProjectTheme.shape.box
                )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.main_create_room),
                    style = ProjectTheme.typography.m.medium
                )
            }


        }
    }
}