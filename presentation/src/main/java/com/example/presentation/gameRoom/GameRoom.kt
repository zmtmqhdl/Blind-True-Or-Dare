package com.example.presentation.gameRoom

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.ProjectScreen
import com.example.domain.model.Room
import com.example.domain.model.RoomStatus

@Composable
fun GameRoomRoute(
    popBackStack: () -> Unit
) {
    // view model
    val gameRoomViewModel: GameRoomViewModel = hiltViewModel()

    // question view model state value
    val room by gameRoomViewModel.room.collectAsState()

    // local state
    var questionValue by remember { mutableStateOf("") }
    // 질문 이벤트 받아서 넣는거 해보자

    // effect

    BackHandler {
        // 종료 팝업 띄워줘야함
    }

    GameRoomScreen(
        room = room,

    )
}

@Composable
fun GameRoomScreen(
    room: Room?,

) {
    ProjectScreen.Screen {



        Text(
            text = "남은 시간: / 총 시간: ${room?.writeTime ?: 0L}s"
        )

        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            if (room?.roomStatus == RoomStatus.WRITE) {

            } else if (room?.roomStatus == RoomStatus.ANSWER) {

            }




            Text(
                text = "문제 수: ${room?.questionNumber ?: 0}"
            )
        }
    }
}