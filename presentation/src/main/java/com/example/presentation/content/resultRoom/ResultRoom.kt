package com.example.presentation.content.resultRoom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.Icon.Back
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectIcon
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
import com.example.domain.model.Question
import com.example.domain.model.Room
import com.example.presentation.R

@Composable
fun ResultRoomRoute(
    popBackStack: () -> Unit
) {
    // view model
    val resultRoomViewModel: ResultRoomViewModel = hiltViewModel()

    // view model state value
    val room by resultRoomViewModel.room.collectAsState()
    val myQuestionList by resultRoomViewModel.myQuestionList.collectAsState()
    val myAnswerList by resultRoomViewModel.myAnswerList.collectAsState()

    // local state
    var selectedQuestion by remember { mutableStateOf<Question?>(null) }

    if (selectedQuestion != null) {

    }

    // screen
    ResultRoomScreen(
        room = room!!,
        onSelectedQuestionClick = {
            selectedQuestion = it
        },
        onShareClick = {

        },
        onExitClick = {

        },
        popBackStack = popBackStack
    )
}

@Composable
fun ResultRoomScreen(
    room: Room,
    onSelectedQuestionClick: (Question) -> Unit,
    onShareClick: () -> Unit,
    onExitClick: () -> Unit,
    popBackStack: () -> Unit
) {
    ProjectScreen.Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = ProjectTheme.space.space2),
                contentAlignment = Alignment.Center
            ) {
                ProjectIcon(
                    icon = Back,
                    modifier = Modifier.align(alignment = Alignment.CenterStart),
                    size = ProjectSpaces.Space8,
                    onClick = popBackStack
                )

                Text(
                    text = stringResource(R.string.result_room_title),
                    modifier = Modifier.align(alignment = Alignment.Center),
                    style = ProjectTheme.typography.l.medium,
                    color = ProjectTheme.color.primary.fontColor
                )
            }
        },
        bottomBar = {
            Column {
                Spacer(modifier = Modifier.height(ProjectSpaces.Space4))

                ProjectButton.Primary.Xlarge(
                    text = stringResource(R.string.component_start),
                    onClick = { }
                )

                Spacer(modifier = Modifier.height(ProjectSpaces.Space7))
            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(ProjectTheme.space.space12))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "제목"
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(0.2f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "O"
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(0.2f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "X"
                        )
                    }
                }

                LazyColumn {
                    item {

                    }

                    items(room.questionList) {
                        QuestionBox(
                            question = it,
                            onClick = { onSelectedQuestionClick(it) }
                        )
                    }
                }
            }
        }
    )


}

@Composable
fun QuestionBox(
    question: Question,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(0.6f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = question.question
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = question.oVoters.size.toString()
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = question.xVoters.size.toString()
            )
        }
    }
}
