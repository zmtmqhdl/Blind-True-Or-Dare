package com.example.presentation.waitingRoom

import android.content.ClipData
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.toClipEntry
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.Icon.Back
import com.example.core.component.ProjectButton
import com.example.core.component.ProjectIcon
import com.example.core.component.ProjectScreen
import com.example.core.theme.ProjectShapes
import com.example.core.theme.ProjectSpace
import com.example.core.theme.ProjectSpaces
import com.example.core.theme.ProjectTheme
import com.example.domain.model.Room
import com.example.domain.model.RoomStatus
import com.example.presentation.R
import kotlinx.coroutines.launch

@Composable
fun WaitingRoomRoute(
    navigateToGameRoom: () -> Unit,
    popBackStack: () -> Unit
) {
    // view model
    val waitingRoomViewModel: WaitingRoomViewModel = hiltViewModel()

    // waiting room view model state value
    val waitingRoom by waitingRoomViewModel.room.collectAsState()
    val qrCode by waitingRoomViewModel.qrCode.collectAsState()

    // local state
    val displayRoomId by remember { mutableStateOf(waitingRoom!!.roomId.chunked(4).joinToString("-")) }
    val clipboard = LocalClipboard.current
    val scope = rememberCoroutineScope()
    val clipData = ClipData.newPlainText("label", waitingRoom!!.roomId)

    // effect
    LaunchedEffect(Unit) {
        waitingRoomViewModel.handleWebSocketConnect(
            onDisconnect = popBackStack
        )
    }

    // 이벤트로 바꿔라...?
    LaunchedEffect(waitingRoom?.roomStatus) {
        if (waitingRoom?.roomStatus == RoomStatus.WRITE) {
            navigateToGameRoom()
        }
    }

    BackHandler {
        waitingRoomViewModel.exitRoom()
    }

    WaitingRoomScreen(
        room = waitingRoom,
        displayRoomId = displayRoomId,
        onRoomIdClick = {
            scope.launch {
                clipboard.setClipEntry(clipData.toClipEntry())
            }
        },
        qrCode = qrCode,
        popBackStack = { waitingRoomViewModel.exitRoom() },
        onStartClick = { waitingRoomViewModel.sendStartMessage() }
    )
}

@Composable
fun WaitingRoomScreen(
    room: Room?,
    displayRoomId: String,
    onRoomIdClick: () -> Unit,
    qrCode: ImageBitmap?,
    popBackStack: () -> Unit,
    onStartClick: () -> Unit
) {
    ProjectScreen.Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ProjectIcon(
                    icon = Back,
                    modifier = Modifier.align(alignment = Alignment.CenterStart),
                    size = ProjectSpaces.Space8,
                    onClick = popBackStack
                )

                Text(
                    text = stringResource(R.string.waiting_room_title),
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
                    text = stringResource(R.string.waiting_room_start),
                    onClick = onStartClick
                )

                Spacer(modifier = Modifier.height(ProjectSpaces.Space7))
            }

        },
        content = {
            Spacer(modifier = Modifier.height(ProjectSpaces.Space8))

            Row(
                modifier = Modifier.fillMaxWidth().height(160.dp).border(
                    width = 1.dp,
                    color = ProjectTheme.color.primary.outline,
                    shape = ProjectShapes.Box
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))

                qrCode?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "QR Code",
                        modifier = Modifier.size(140.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(ProjectSpaces.Space2))

                Column(

                ) {
                    Text(
                        text = stringResource(R.string.component_waiting_room_id)
                    )
                    Text(
                        text = displayRoomId,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onRoomIdClick
                        )
                    )

                    Spacer(modifier = Modifier.width(ProjectSpaces.Space2))

                    Text(
                        text = stringResource(R.string.component_host_nickname)
                    )

                    Text(
                        text = room!!.host.nickname
                    )
                }

            }

            Spacer(modifier = Modifier.height(ProjectTheme.space.space4))

            LazyColumn() {
                // 떠들 예정인 사람
                item {
                    Text(
                        text = ""
                    )
                }

                items(room!!.participantList.toList()) {
                    
                }
            }

            Text(
                text = room?.participantList?.map { it.nickname }.toString(),
                style = ProjectTheme.typography.m.medium
            )

            Spacer(modifier = Modifier.height(ProjectTheme.space.space4))





        }
    )
}