package com.example.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.presentation.R
import com.example.presentation.component.MyWorldButton
import com.example.presentation.component.MyWorldTextField
import com.example.presentation.component.PrimaryDialog
import com.example.presentation.screen.MyWorldScreen
import com.example.presentation.theme.ProjectSpaces

@Composable
fun MainRoute() {
    // local state
    var waitingRoomTitle by remember { mutableStateOf("")}

    // dialog
    var showInputNicknameDialog by remember { mutableStateOf(false) }
    if (showInputNicknameDialog) {
        PrimaryDialog(
            text = stringResource(R.string.main_input_nickname_dialog_text),
            content = {
                MyWorldTextField.OutlinedTextField(
                    value = waitingRoomTitle,
                    onValueChange = { waitingRoomTitle = it },
                )
            },
            onDismissRequest = { showInputNicknameDialog = false }
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
    MyWorldScreen.PrimaryScreen {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyWorldButton.CTA.Xlarge(
                text = stringResource(R.string.main_create_room),
                onClick = onCreateClick
            )

            Spacer(modifier = Modifier.height(ProjectSpaces.Space3))

            MyWorldButton.CTA.Xlarge(
                text = stringResource(R.string.main_join_room),
                onClick = onJoinClick
            )
        }
    }
}