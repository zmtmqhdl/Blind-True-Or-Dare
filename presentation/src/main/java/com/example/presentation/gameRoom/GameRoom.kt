package com.example.presentation.gameRoom

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.Icon.Back
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectDialog.Double.RowArrangement
import com.example.core.component.ProjectIcon
import com.example.core.component.ProjectScreen
import com.example.core.component.ProjectTextField
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
import com.example.domain.model.Room
import com.example.domain.model.RoomStatus
import com.example.presentation.R
import kotlinx.coroutines.launch

@Composable
fun GameRoomRoute(
    navigateToResultRoom: () -> Unit,
    popBackStack: () -> Unit
) {
    // view model
    val gameRoomViewModel: GameRoomViewModel = hiltViewModel()

    // question view model state value
    val room by gameRoomViewModel.room.collectAsState()
    val time by gameRoomViewModel.time.collectAsState()
    val currentQuestionNumber by gameRoomViewModel.currentQuestionNumber.collectAsState()
    val isStart by gameRoomViewModel.isStart.collectAsState()

    // local state
    var questionValue by remember { mutableStateOf("") }
    var exitDialog by remember { mutableStateOf(false) }


    // dialog
    if (exitDialog) {
        RowArrangement(
            title = stringResource(R.string.game_room_exit_dialog_title),
            text = stringResource(R.string.game_room_exit_dialog_message),
            buttonText1 = stringResource(R.string.component_cancel),
            buttonText2 = stringResource(R.string.component_okay),
            onClick1 = {
                exitDialog = false
            },
            onClick2 = {
                exitDialog = false
                gameRoomViewModel.exitRoom()
            },
            onDismissRequest = { exitDialog = false }
        )

    }

    // effect
    LaunchedEffect(Unit) {

        launch {
            gameRoomViewModel.eventHandler(
                writeNextQuestion = {
                    gameRoomViewModel.submitQuestion(
                        questionValue = questionValue,
                    )
                    questionValue = ""
                },
                answerNextQuestion = {
                    gameRoomViewModel.submitAnswer(
                        voteValue = null,
                    )
                }
            )
        }

        launch {
            gameRoomViewModel.handleWebSocketConnect(
                onDisconnect = popBackStack
            )
        }
    }

    LaunchedEffect(room?.roomStatus) {
        if (room?.roomStatus == RoomStatus.END) {
            navigateToResultRoom()
        }
    }

    BackHandler {
        exitDialog = true
    }

    GameRoomScreen(
        room = room,
        questionValue = questionValue,
        onSubmitClick = {
            gameRoomViewModel.submitQuestion(
                questionValue = questionValue,
            )
            questionValue = ""
        },
        currentQuestionNumber = currentQuestionNumber,
        updateQuestionValue = { questionValue = it },
        onOVoteClick = {
            gameRoomViewModel.submitAnswer(
                voteValue = true
            )
        },
        onXVoteClick = {
            gameRoomViewModel.submitAnswer(
                voteValue = false
            )
        },
        time = time,
        popBackStack = { exitDialog = true },
        isStart = isStart
    )
}

@Composable
fun GameRoomScreen(
    room: Room?,
    questionValue: String,
    currentQuestionNumber: Int,
    onSubmitClick: () -> Unit,
    updateQuestionValue: (String) -> Unit,
    onOVoteClick: () -> Unit,
    onXVoteClick: () -> Unit,
    time: Long,
    popBackStack: () -> Unit,
    isStart: Boolean
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
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.border(
                    width = ProjectSpaces.Space0,
                    color = ProjectTheme.color.primary.outline,
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // todo - 같은 높이
                if (isStart) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.game_room_guide_message),
                        )

                        Spacer(modifier = Modifier.height(ProjectSpaces.Space2))

                        Text(
                            text = "$time"
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (room?.roomStatus) {
                                RoomStatus.WRITE -> "Q.$currentQuestionNumber"
                                RoomStatus.ANSWER -> "A.$currentQuestionNumber"
                                else -> ""
                            },
                            modifier = Modifier.align(alignment = Alignment.CenterStart)
                        )

                        Text(
                            text = "남은 시간: $time / 총 시간: ${room?.writeTime ?: 0L}s",
                            modifier = Modifier.align(alignment = Alignment.Center)
                        )
                    }
                }
            }







            if (room?.roomStatus == RoomStatus.WRITE) {
                ProjectTextField.OutlinedTextField(
                    value = questionValue,
                    onValueChange = {
                        updateQuestionValue(it)
                    }
                )

            } else if (room?.roomStatus == RoomStatus.ANSWER) {
                Text(
                    text = room.questionList[currentQuestionNumber - 1].question
                )
            }

            Spacer(modifier = Modifier.height(ProjectSpaces.Space4))

            if (room?.roomStatus == RoomStatus.ANSWER) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .height(100.dp)
                            .border(
                                width = ProjectTheme.space.space1,
                                color = ProjectTheme.color.primary.outline,
                                shape = ProjectTheme.shape.button
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onOVoteClick
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "O",
                            color = ProjectTheme.color.primary.fontColor,
                            style = ProjectTheme.typography.xxxl.medium
                        )
                    }

                    Spacer(modifier = Modifier.width(ProjectTheme.space.space4))

                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .height(100.dp)
                            .border(
                                width = ProjectTheme.space.space1,
                                color = ProjectTheme.color.primary.outline,
                                shape = ProjectTheme.shape.button
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onXVoteClick
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "X",
                            color = ProjectTheme.color.primary.fontColor,
                            style = ProjectTheme.typography.xxxl.medium
                        )
                    }
                }
            } else if (room?.roomStatus == RoomStatus.ANSWER) {
                ProjectButton.Primary.Medium(
                    text = stringResource(R.string.component_submit),
                    onClick = onSubmitClick
                )
            }
        }
    }
}