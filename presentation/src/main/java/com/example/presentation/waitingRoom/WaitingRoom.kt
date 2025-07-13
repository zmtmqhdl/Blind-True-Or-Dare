package com.example.presentation.waitingRoom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectTheme
import com.example.domain.model.WaitingRoom
import com.example.presentation.R

@Composable
fun WaitingRoomRoute() {

    // view model
    val waitingRoomViewModel: WaitingRoomViewModel = hiltViewModel()

    // waiting room view model state value
    val waitingRoom by waitingRoomViewModel.waitingRoom.collectAsState()

    WaitingRoomScreen(
        waitingRoom = waitingRoom
    )
}

@Composable
fun WaitingRoomScreen(
    waitingRoom: WaitingRoom?
) {
    ProjectScreen.Screen {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.main_create_room),
                style = ProjectTheme.typography.m.medium
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

            Text(
                text = waitingRoom?.participantList!!.map{it.nickname}.toString(),
                style = ProjectTheme.typography.m.medium
            )
        }
    }
}