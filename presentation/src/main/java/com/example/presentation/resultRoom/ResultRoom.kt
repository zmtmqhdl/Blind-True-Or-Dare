package com.example.presentation.resultRoom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectTheme

@Composable
fun ResultRoomRoute(
    popBackStack: () -> Unit
) {
    // result view model
    val resultRoomViewModel: ResultRoomViewModel = hiltViewModel()

    // result view model state value
    val room by resultRoomViewModel.room.collectAsState()
    val myQuestionList by resultRoomViewModel.myQuestionList.collectAsState()
    val myAnswerList by resultRoomViewModel.myAnswerList.collectAsState()

    // screen
    ResultRoomScreen(
        popBackStack = popBackStack
    )
}

@Composable
fun ResultRoomScreen(
    popBackStack: () -> Unit
) {
    ProjectScreen.Screen {
        Spacer(modifier = Modifier.height(ProjectTheme.space.space12))

        Text(
            text = "제목",
        )

        Box(
            modifier = Modifier
        ) {

        }

        LazyColumn {

        }

    }

}