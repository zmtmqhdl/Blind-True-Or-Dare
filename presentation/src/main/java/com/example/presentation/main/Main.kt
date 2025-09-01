package com.example.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.PrimaryDialog
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectDialog
import com.example.core.component.ProjectScreen
import com.example.core.component.ProjectTextField
import com.example.core.core.ProjectPreview
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
import com.example.domain.usecase.RoomIdTransformationUseCase
import com.example.presentation.R
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    navigateToWaitingRoom: () -> Unit
) {
    // view model
    val mainViewModel: MainViewModel = hiltViewModel()

    // main view model state value
    val createRoomFailureDialog by mainViewModel.createRoomFailureDialog.collectAsState()
    val joinRoomFailureDialog by mainViewModel.joinRoomFailureDialog.collectAsState()

    // local state
    var nickname by remember { mutableStateOf("") }
    var roomId by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    // dialog
    var createWaitingRoomDialog by remember { mutableStateOf(false) }
    if (createWaitingRoomDialog) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        PrimaryDialog(
            title = stringResource(R.string.main_create_room),
            content = {
                Text(
                    text = stringResource(R.string.main_dialog_input_nickname),
                    style = ProjectTheme.typography.s.medium,
                    color = ProjectTheme.color.primary.fontColor
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

                ProjectTextField.OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    modifier = Modifier.focusRequester(focusRequester = focusRequester),
                    textCenter = true
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                ProjectButton.Primary.Small(
                    text = stringResource(R.string.component_create),
                    onClick = {
                        mainViewModel.createRoom(
                            nickname = nickname
                        )
                        createWaitingRoomDialog = false
                    },
                    enabled = nickname.isNotEmpty()
                )
            },
            onDismissRequest = {
                nickname = ""
                createWaitingRoomDialog = false
            }
        )
    }

    var joinWaitingRoomDialog by remember { mutableStateOf(false) }
    if (joinWaitingRoomDialog) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        PrimaryDialog(
            title = stringResource(R.string.main_join_room),
            content = {
                Text(
                    text = stringResource(R.string.main_dialog_input_nickname),
                    style = ProjectTheme.typography.s.medium,
                    color = ProjectTheme.color.primary.fontColor
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

                ProjectTextField.OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    modifier = Modifier.focusRequester(focusRequester = focusRequester),
                    textCenter = true
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                Text(
                    text = stringResource(R.string.main_dialog_input_room_id),
                    style = ProjectTheme.typography.s.medium,
                    color = ProjectTheme.color.primary.fontColor
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

                ProjectTextField.OutlinedTextField(
                    value = roomId,
                    onValueChange = { roomId = it },
                    modifier = Modifier.focusRequester(focusRequester = focusRequester),
                    textCenter = true,
                    visualTransformation = RoomIdTransformationUseCase()
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                ProjectButton.Primary.Small(
                    text = stringResource(R.string.component_enter),
                    onClick = {
                        mainViewModel.joinRoom(
                            nickname = nickname,
                            roomId = roomId
                        )
                        joinWaitingRoomDialog = false
                    },
                    enabled = nickname.isNotEmpty()
                )
            },
            onDismissRequest = {
                nickname = ""
                roomId = ""
                joinWaitingRoomDialog = false
            }
        )
    }

    if (createRoomFailureDialog) {
        ProjectDialog.Single.SingleArrangement(
            title = stringResource(R.string.main_create_room_failure_dialog_title),
            text = stringResource(R.string.main_create_room_failure_dialog_text),
            buttonText = stringResource(R.string.component_okay),
            onClick = { mainViewModel.dismissCreateRoomFailureDialog() },
            onDismissRequest = { mainViewModel.dismissCreateRoomFailureDialog() }
        )
    }

    if (joinRoomFailureDialog) {
        ProjectDialog.Single.SingleArrangement(
            title = stringResource(R.string.main_join_room_failure_dialog_title),
            text = stringResource(R.string.main_join_room_failure_dialog_text),
            buttonText = stringResource(R.string.component_okay),
            onClick = { mainViewModel.dismissJoinRoomFailureDialog() },
            onDismissRequest = { mainViewModel.dismissJoinRoomFailureDialog() }
        )
    }

    // effect
    LaunchedEffect(Unit) {
        launch {
            mainViewModel.handleWebSocketConnect(
                onConnect = { navigateToWaitingRoom() }
            )
        }
    }

    // screen
    MainScreen(
        onCreateClick = { createWaitingRoomDialog = true },
        onJoinClick = { joinWaitingRoomDialog = true }
    )
}

@Composable
fun MainScreen(
    onCreateClick: () -> Unit,
    onJoinClick: () -> Unit
) {
    ProjectScreen.Screen {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BLIND",
                modifier = Modifier.height(100.dp),
                style = ProjectTheme.typography.xl.bold,
                fontSize = 90.sp,
                color = ProjectTheme.color.primary.fontColor
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TRUE",
                    style = ProjectTheme.typography.xl.bold,
                    fontSize = 45.sp,
                    color = ProjectTheme.color.primary.fontColor
                )

                Spacer(modifier = Modifier.width(ProjectTheme.space.space2))

                Text(
                    text = "OR",
                    style = ProjectTheme.typography.m.bold,
                    fontSize = 25.sp,
                    color = ProjectTheme.color.primary.fontColor
                )

                Spacer(modifier = Modifier.width(ProjectTheme.space.space2))

                Text(
                    text = "DARE",
                    style = ProjectTheme.typography.xl.bold,
                    fontSize = 45.sp,
                    color = ProjectTheme.color.primary.fontColor
                )
            }

            Spacer(modifier = Modifier.height(ProjectTheme.space.space12 * 2))

            Box(
                modifier = Modifier
                    .width(330.dp)
                    .height(100.dp)
                    .background(
                        color = ProjectTheme.color.background,
                        shape = ProjectTheme.shape.button
                    )
                    .border(
                        width = ProjectTheme.space.space1,
                        color = ProjectTheme.color.primary.outline,
                        shape = ProjectTheme.shape.button
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onCreateClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.main_create_room),
                    color = ProjectTheme.color.primary.fontColor,
                    fontSize = 40.sp,
                    style = ProjectTheme.typography.xl.medium
                )
            }



            Spacer(modifier = Modifier.height(ProjectSpaces.Space8))

            Box(
                modifier = Modifier
                    .width(330.dp)
                    .height(100.dp)
                    .background(
                        color = ProjectTheme.color.background,
                        shape = ProjectTheme.shape.button
                    )
                    .border(
                        width = ProjectTheme.space.space1,
                        color = ProjectTheme.color.primary.outline,
                        shape = ProjectTheme.shape.button
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onJoinClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.main_join_room),
                    color = ProjectTheme.color.primary.fontColor,
                    fontSize = 40.sp,
                    style = ProjectTheme.typography.xl.medium

                )
            }
        }

    }
}

@ProjectPreview
@Composable
fun MainScreenPreview() {
    ProjectTheme {
        MainScreen(
            onCreateClick = {},
            onJoinClick = {}
        )
    }
}