package com.example.presentation.questionRoom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.ProjectScreen
import com.example.domain.model.QuestionRoomSetting

@Composable
fun QuestionRoomRoute(
    popBackStack: () -> Unit
) {
    // view model
    val questionRoomViewModel: QuestionRoomViewModel = hiltViewModel()

    // question room view model state value
    val questionRoomSetting by questionRoomViewModel.questionRoomSetting.collectAsState()

    QuestionRoomScreen(
        questionRoomSetting = questionRoomSetting,
        popBackStack = popBackStack
    )
}

@Composable
fun QuestionRoomScreen(
    questionRoomSetting: QuestionRoomSetting?,
    popBackStack: () -> Unit
) {
    ProjectScreen.Screen {
        Text(
            text = "${questionRoomSetting?.time}  //   ${questionRoomSetting?.number}"
        )
    }
}