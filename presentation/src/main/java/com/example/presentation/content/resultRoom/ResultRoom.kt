package com.example.presentation.content.resultRoom

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.core.Icon.OpenedFolder
import com.example.core.component.PrimaryDialog
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectDialog.Double.RowArrangement
import com.example.core.component.ProjectIcon
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
import com.example.domain.model.Question
import com.example.domain.model.Room
import com.example.presentation.R
import kotlinx.coroutines.launch

@Composable
fun ResultRoomRoute(
    navigateToWaitingRoom: () -> Unit,
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
    var exitDialog by remember { mutableStateOf(false) }


    // dialog
    if (exitDialog) {
        RowArrangement(
            title = stringResource(R.string.component_exit_game),
            text = stringResource(R.string.game_room_exit_dialog_message),
            buttonText1 = stringResource(R.string.component_cancel),
            buttonText2 = stringResource(R.string.component_okay),
            onClick1 = {
                exitDialog = false
            },
            onClick2 = {
                exitDialog = false
                resultRoomViewModel.exitRoom()
            },
            onDismissRequest = { exitDialog = false }
        )

    }

    if (selectedQuestion != null) {
        PrimaryDialog {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = ProjectSpaces.Space0,
                            color = ProjectTheme.color.primary.outline

                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = selectedQuestion!!.question
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.4f)
                            .border(
                                width = ProjectSpaces.Space0,
                                color = ProjectTheme.color.primary.outline

                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedQuestion!!.oVoter.size.toString()
                        )
                    }

                    Spacer(modifier = Modifier.width(ProjectSpaces.Space4))

                    Box(
                        modifier = Modifier
                            .weight(0.4f)
                            .border(
                                width = ProjectSpaces.Space0,
                                color = ProjectTheme.color.primary.outline

                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedQuestion!!.noAnswer.size.toString()
                        )
                    }

                    Spacer(modifier = Modifier.width(ProjectSpaces.Space4))

                    Box(
                        modifier = Modifier
                            .weight(0.4f)
                            .border(
                                width = ProjectSpaces.Space0,
                                color = ProjectTheme.color.primary.outline

                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedQuestion!!.xVoter.size.toString()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(ProjectSpaces.Space10))

                ProjectButton.Primary.Medium(
                    text = stringResource(R.string.component_okay),
                    onClick = { selectedQuestion = null }
                )

            }
        }
    }

    // side effect
    LaunchedEffect(Unit) {
        launch {
            resultRoomViewModel.eventHandler(
                webSocketRejoin = navigateToWaitingRoom
            )
        }
    }

    BackHandler {
        exitDialog = true
    }

    // screen
    ResultRoomScreen(
        room = room!!,
        onSelectedQuestionClick = {
            selectedQuestion = it
        },
        onReJoinClick = { resultRoomViewModel.rejoinRoom() },
        onShareClick = {

        },
        onExitClick = {
            resultRoomViewModel.exitRoom()
        },
        popBackStack = popBackStack
    )
}

@Composable
fun ResultRoomScreen(
    room: Room,
    onSelectedQuestionClick: (Question) -> Unit,
    onReJoinClick: () -> Unit,
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

                Row {
                    ProjectButton.Primary.Xlarge(
                        text = stringResource(R.string.component_re_join),
                        modifier = Modifier.fillMaxWidth(0.5f),
                        onClick = onReJoinClick
                    )

                    Spacer(modifier = Modifier.height(ProjectSpaces.Space3))

                    ProjectButton.Primary.Xlarge(
                        text = stringResource(R.string.component_exit_game),
                        modifier = Modifier.fillMaxWidth(0.5f),
                        onClick = onExitClick
                    )
                }


                Spacer(modifier = Modifier.height(ProjectSpaces.Space7))
            }
        },
        content = {
            if (room.questionList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProjectIcon(
                        icon = OpenedFolder,
                        size = ProjectSpaces.Space12
                    )

                    Text(
                        text = stringResource(R.string.result_room_no_question)
                    )

                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(ProjectTheme.space.space12))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(0.4f),
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
                        Box(
                            modifier = Modifier.fillMaxWidth(0.2f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.component_no_answer)
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
            modifier = Modifier.fillMaxWidth(0.4f),
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
                text = question.oVoter.size.toString()
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = question.xVoter.size.toString()
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = question.noAnswer.size.toString()
            )
        }
    }
}
