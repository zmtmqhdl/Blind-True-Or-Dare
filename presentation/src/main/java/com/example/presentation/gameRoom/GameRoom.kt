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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.ProjectScreen
import com.example.domain.model.Room

@Composable
fun GameRoomRoute(
    popBackStack: () -> Unit
) {
    // view model
    val gameRoomViewModel: GameRoomViewModel = hiltViewModel()

    // question view model state value
    val room by gameRoomViewModel.room.collectAsState()

    // local state
    val totalTime = room?.writeTime ?: 0L
    var remainingTime by remember { mutableLongStateOf(totalTime) }
    val progress = remember { Animatable(1f) }

    // effect
    LaunchedEffect(totalTime) {
        if (totalTime > 0L) {
            val startTime = withFrameNanos { it }
            var elapsed: Long
            do {
                elapsed = (withFrameNanos { it } - startTime) / 1_000_000
                val clamped = elapsed.coerceAtMost(totalTime * 1000L)
                remainingTime = ((totalTime * 1000L - clamped) / 1000L).coerceAtLeast(0L)
                progress.snapTo(1f - clamped.toFloat() / (totalTime * 1000L))

            } while (clamped < totalTime * 1000L)
        }
    }

    BackHandler {
        // 종료 팝업 띄워줘야함
    }

    GameRoomScreen(
        room = room,
        progress = progress.value,
        remainingTime = remainingTime,
    )
}

@Composable
fun GameRoomScreen(
    room: Room?,
    progress: Float,
    remainingTime: Long,
) {
    ProjectScreen.Screen {

        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxWidth(),
            color = ProgressIndicatorDefaults.linearColor,
            trackColor = ProgressIndicatorDefaults.linearTrackColor,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )

        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            // 이제 여기다가 분기 태워야함
        }

        Text(
            text = "남은 시간: ${remainingTime}s / 총 시간: ${room?.writeTime ?: 0L}s"
        )

        Text(
            text = "문제 수: ${room?.questionNumber ?: 0}"
        )
    }
}