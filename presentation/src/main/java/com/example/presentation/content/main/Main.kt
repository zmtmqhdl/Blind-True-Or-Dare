package com.example.presentation.content.main

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core.component.PrimaryDialog
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectDialog
import com.example.core.component.ProjectScreen
import com.example.core.component.ProjectTextField
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
import com.example.presentation.R
import com.example.presentation.util.permissionRequestLauncher
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    navigateToScanQrCode: () -> Unit,
    navigateToWaitingRoom: () -> Unit
) {
    // view model
    val mainViewModel: MainViewModel = hiltViewModel()

    // model state value
    val createRoomFailureDialog by mainViewModel.createRoomFailureDialog.collectAsState()
    val joinRoomFailureDialog by mainViewModel.joinRoomFailureDialog.collectAsState()

    // local state
    var nickname by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var createWaitingRoomDialog by remember { mutableStateOf(false) }
    val permissionRequestLauncher = permissionRequestLauncher()
    val context = LocalContext.current
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    var permissionRequestDialog by remember { mutableStateOf(false) }

    // dialog
    if (createWaitingRoomDialog) {
        PrimaryDialog(
            title = stringResource(R.string.main_create_room),
            onDismissRequest = {
                nickname = ""
                createWaitingRoomDialog = false
            }
        ) {
            Text(
                text = stringResource(id = R.string.component_nickname),
                style = ProjectTheme.typography.s.medium,
                color = ProjectTheme.color.primary.fontColor
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space2))

            ProjectTextField.OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                modifier = Modifier.focusRequester(focusRequester = focusRequester),
                textCenter = true,
                placeholder = stringResource(id = R.string.main_dialog_create_room_dialog_placeholder)
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

            Row {
                ProjectButton.Primary.Small(
                    text = stringResource(R.string.component_cancel),
                    onClick = {
                        createWaitingRoomDialog = false
                        nickname = ""
                    },
                    modifier = Modifier.weight(0.5f),
                )

                Spacer(modifier = Modifier.width(ProjectSpaces.Space4))

                ProjectButton.Primary.Small(
                    text = stringResource(R.string.component_create),
                    onClick = {
                        mainViewModel.createRoom(
                            nickname = nickname
                        )
                        createWaitingRoomDialog = false
                    },
                    modifier = Modifier.weight(0.5f),
                    enabled = nickname.length in 1..20
                )
            }
        }
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

    if (permissionRequestDialog) {
        ProjectDialog.Double.RowArrangement(
            title = stringResource(R.string.main_permission_request_dialog_title),
            text = stringResource(R.string.main_permission_request_dialog_message),
            buttonText1 = stringResource(R.string.component_cancel),
            buttonText2 = stringResource(R.string.component_okay),
            onClick1 = { permissionRequestDialog = false },
            onClick2= {
                permissionRequestDialog = false
                context.startActivity(intent)
            }
        )
    }

    // side effect
    LaunchedEffect(createWaitingRoomDialog) {
        if (createWaitingRoomDialog) {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(Unit) {
        launch {
            mainViewModel.handleWebSocketConnect(
                onConnect = navigateToWaitingRoom
            )
        }
    }

    // screen
    MainScreen(
        onCreateClick = { createWaitingRoomDialog = true },
        onJoinClick = {
            permissionRequestLauncher(
                listOf(Manifest.permission.CAMERA),
                { navigateToScanQrCode() },
                {},
                { permissionRequestDialog = true }
            )
        }
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
                    text = stringResource(R.string.component_join_room),
                    color = ProjectTheme.color.primary.fontColor,
                    fontSize = 40.sp,
                    style = ProjectTheme.typography.xl.medium

                )
            }
        }

    }
}