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
import com.example.presentation.R
import com.example.presentation.component.PrimaryDialog
import com.example.presentation.component.ProjectButton
import com.example.presentation.component.ProjectDialog
import com.example.presentation.component.ProjectScreen
import com.example.presentation.component.ProjectTextField
import com.example.presentation.theme.ProjectSpaces
import com.example.presentation.theme.ProjectTheme
import com.example.presentation.waitingRoom.WaitingRoomEvent
import com.example.presentation.waitingRoom.WaitingRoomViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    mainViewModel: MainViewModel,
    waitingRoomViewModel: WaitingRoomViewModel,
    navigateToWaitingRoom: () -> Unit
) {
    val isLoading by mainViewModel.isLoading.collectAsState()

    // local state
    var nickname by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    // dialog
    var showInputNicknameDialog by remember { mutableStateOf(false) }
    if (showInputNicknameDialog) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        PrimaryDialog(
            text = stringResource(R.string.main_input_nickname_dialog_text),
            content = {
                ProjectTextField.OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    modifier = Modifier.focusRequester(focusRequester = focusRequester),
                    textCenter = true
                )

                Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

                ProjectButton.Primary.Small(
                    text = stringResource(R.string.main_input_nickname_dialog_okay),
                    onClick = {
                        mainViewModel.createWaitingRoom(
                            nickname = nickname
                        )
                        showInputNicknameDialog = false
                    },
                    enabled = nickname.isNotEmpty()
                )
            },
            onDismissRequest = {
                nickname = ""
                showInputNicknameDialog = false
            }
        )
    }

    var showCreateWaitingRoomFailureDialog by remember { mutableStateOf(false) }
    if (showCreateWaitingRoomFailureDialog) {
        ProjectDialog.Single.SingleArrangement(
            title = "",
            text = "",
            buttonText = "",
            onClick = { showCreateWaitingRoomFailureDialog = false },
            onDismissRequest = { showCreateWaitingRoomFailureDialog = false}
        )
    }

    // launched effect
    LaunchedEffect(Unit) {
        launch {
            mainViewModel.event.collectLatest { event ->
                when (event) {
                    is MainEvent.CreateWaitingRoomSuccess -> {
                        waitingRoomViewModel.connectWaitingRoom(
                            waitingRoomId = event.waitingRoomId,
                            playerId = event.playerId
                        )
                    }
                    is MainEvent.CreateWaitingRoomFailure -> {
                        showCreateWaitingRoomFailureDialog = true
                    }
                    else -> {}
                }
            }
        }

        launch {
            waitingRoomViewModel.event.collectLatest { event ->
                when (event) {
                    is WaitingRoomEvent.ConnectWaitingRoomSuccess -> {
                        navigateToWaitingRoom()
                    }
                    else -> {}
                }
            }
        }


        launch {
            waitingRoomViewModel.socketMessage.collectLatest { message ->
                message?.let {

                }
            }
        }
    }



    // screen
    MainScreen(
        loading = isLoading,
        onCreateClick = { showInputNicknameDialog = true },
        onJoinClick = {}
    )
}

@Composable
fun MainScreen(
    loading: Boolean,
    onCreateClick: () -> Unit,
    onJoinClick: () -> Unit
) {
    ProjectScreen.PrimaryScreen(
        loading = loading
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
}