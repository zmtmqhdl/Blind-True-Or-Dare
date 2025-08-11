package com.example.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.component.PrimaryDialog
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectDialog
import com.example.core.component.ProjectTextField
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
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

    // local state
    var nickname by remember { mutableStateOf("") }
    var waitingRoomId by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    // dialog
    var createWaitingRoomDialog by remember { mutableStateOf(false) }
    if (createWaitingRoomDialog) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        PrimaryDialog(
            text = stringResource(R.string.main_create_room),
            content = {
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
            text = stringResource(R.string.main_join_room),
            content = {
                ProjectTextField.OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    modifier = Modifier.focusRequester(focusRequester = focusRequester),
                    textCenter = true
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                ProjectTextField.OutlinedTextField(
                    value = waitingRoomId,
                    onValueChange = { waitingRoomId = it },
                    modifier = Modifier.focusRequester(focusRequester = focusRequester),
                    textCenter = true
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                ProjectButton.Primary.Small(
                    text = stringResource(R.string.component_enter),
                    onClick = {
                        mainViewModel.joinRoom(
                            nickname = nickname,
                            roomId = waitingRoomId
                        )
                        joinWaitingRoomDialog = false
                    },
                    enabled = nickname.isNotEmpty()
                )
            },
            onDismissRequest = {
                nickname = ""
                waitingRoomId = ""
                joinWaitingRoomDialog = false
            }
        )
    }

    if (createRoomFailureDialog) {
        ProjectDialog.Single.SingleArrangement(
            title = "",
            text = "",
            buttonText = "",
            onClick = { mainViewModel.dismissCreateRoomFailureDialog() },
            onDismissRequest = {
                mainViewModel.dismissCreateRoomFailureDialog()
            }
        )
    }

    var joinWaitingRoomFailureDialog by remember { mutableStateOf(false) }
    if (joinWaitingRoomFailureDialog) {
        ProjectDialog.Single.SingleArrangement(
            title = "",
            text = "",
            buttonText = "",
            onClick = { joinWaitingRoomFailureDialog = false },
            onDismissRequest = { joinWaitingRoomFailureDialog = false }
        )
    }

    // effect
    LaunchedEffect(Unit) {

        launch {
            mainViewModel.handleWebSocketConnect(
                onConnect = {


                    navigateToWaitingRoom()
                },
                onConnectFailure = {

                },
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProjectButton.Primary.Xlarge(
            text = stringResource(R.string.main_create_room),
            onClick = onCreateClick
        )

        Spacer(modifier = Modifier.height(ProjectSpaces.Space3))

        ProjectButton.Primary.Xlarge(
            text = stringResource(R.string.main_join_room),
            onClick = onJoinClick
        )
    }
}