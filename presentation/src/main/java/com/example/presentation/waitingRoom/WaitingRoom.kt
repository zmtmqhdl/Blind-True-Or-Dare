package com.example.presentation.waitingRoom

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectTheme
import com.example.domain.model.MessageType
import com.example.domain.model.WaitingRoom
import com.example.domain.model.WaitingRoomStatus
import com.example.presentation.R

@Composable
fun WaitingRoomRoute(
    navigateToQuestionRoom: () -> Unit,
    popBackStack: () -> Unit
) {
    // view model
    val waitingRoomViewModel: WaitingRoomViewModel = hiltViewModel()

    // waiting room view model state value
    val waitingRoom by waitingRoomViewModel.waitingRoom.collectAsState()
    val qrCode by waitingRoomViewModel.qrCode.collectAsState()

    // effect
    LaunchedEffect(Unit) {
        waitingRoomViewModel.handleWebSocketConnect(
            onDisconnect = popBackStack
        )
    }

    // 이벤트로 바꿔라...
    LaunchedEffect(waitingRoom?.waitingRoomStatus) {
        if (waitingRoom?.waitingRoomStatus == WaitingRoomStatus.Playing) {
            navigateToQuestionRoom()
        }
    }

    BackHandler {
        waitingRoomViewModel.exitWaitingRoom()
    }

    WaitingRoomScreen(
        waitingRoom = waitingRoom,
        qrCode = qrCode,

        onStartClick = { waitingRoomViewModel.sendStartMessage() }
    )
}

@Composable
fun WaitingRoomScreen(
    waitingRoom: WaitingRoom?,
    qrCode: ImageBitmap?,

    onStartClick: () -> Unit
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
                text = waitingRoom?.participantList?.map{it.nickname}.toString(),
                style = ProjectTheme.typography.m.medium
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

            qrCode?.let {
                Image(
                    bitmap = it,
                    contentDescription = "QR Code",
                    modifier = Modifier.size(ProjectTheme.space.space12), // 원하는 크기로 설정
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

            ProjectButton.Primary.Xlarge(
                text = stringResource(R.string.waiting_room_start),
                onClick = onStartClick
            )
        }


    }
}