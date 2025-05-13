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
import com.example.presentation.screen.MyWorldScreen
import com.example.presentation.theme.MyWorldTheme

@Composable
fun WaitingRoomScreen() {
    MyWorldScreen.PrimaryScreen {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MyWorldTheme.color.white,
                    shape = MyWorldTheme.shape.box
                )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.main_create_room),
                    style = MyWorldTheme.typography.m.medium
                )
            }


        }
    }
}