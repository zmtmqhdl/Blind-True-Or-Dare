package com.example.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.presentation.component.ProjectButton
import com.example.presentation.component.ProjectTextField
import com.example.presentation.component.PrimaryDialog
import com.example.presentation.screen.ProjectScreen
import com.example.presentation.sharedViewModel.WaitingRoomDataViewModel
import com.example.presentation.theme.ProjectSpaces
import com.example.presentation.theme.ProjectTheme

@Composable
fun MainRoute(
    waitingRoomDataViewModel: WaitingRoomDataViewModel,
    navigateToWaitingRoom: () -> Unit
) {
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
                        waitingRoomDataViewModel.createWaitingRoom(
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

    // screen
    MainScreen(
        onCreateClick = { showInputNicknameDialog = true },
        onJoinClick = {}
    )
}

@Composable
fun MainScreen(
    onCreateClick: () -> Unit,
    onJoinClick: () -> Unit
) {
    ProjectScreen.PrimaryScreen {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProjectButton.CTA.Xlarge(
                text = stringResource(R.string.main_create_room),
                onClick = onCreateClick
            )

            Spacer(modifier = Modifier.height(ProjectSpaces.Space3))

            ProjectButton.CTA.Xlarge(
                text = stringResource(R.string.main_join_room),
                onClick = onJoinClick
            )
        }
    }
}