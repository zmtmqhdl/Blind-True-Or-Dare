package com.example.presentation.sharedViewModel

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.FirebaseRepository
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.example.domain.common.onFailure
import com.example.domain.common.onSuccess
import com.example.domain.model.WaitingRoomStatus
import com.example.domain.model.User
import com.example.domain.model.WaitingRoom
import com.example.domain.repository.RetrofitRepository
import com.example.presentation.core.ProjectViewModel
import java.util.UUID

@HiltViewModel
class WaitingRoomDataViewModel @Inject constructor(
    private val retrofitRepository: RetrofitRepository,
    private val firebaseRepository: FirebaseRepository
) : ProjectViewModel<WaitingRoomDataState, WaitingRoomDataEvent>(
    initialState = WaitingRoomDataState(),
    viewModelTag = "WaitingRoomDataViewModel"
) {

    private val _waitingRoom = MutableStateFlow<WaitingRoom?>(null)
    val waitingRoom: Flow<WaitingRoom?> = _waitingRoom.asStateFlow()

    private val _barCode = MutableStateFlow<Bitmap?>(null)
    val barCode: Flow<Bitmap?> = _barCode.asStateFlow()

    fun createWaitingRoomedd(nickname: String) {
        setState { copy(loading = true) }
        logD(
            """
            [fun createWaitingRoom parameter]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            val roomId = UUID.randomUUID().toString().take(8)
            val userId = UUID.randomUUID().toString()
            firebaseRepository.createWaitingRoom(
                waitingRoom = WaitingRoom(
                    roomId = roomId,
                    hostId = userId,
                    participantList = listOf(User(userId = userId, nickname = nickname)),
                    waitingRoomStatus = WaitingRoomStatus.Waiting,
                )
            ).onSuccess {
                _waitingRoom.value = it
                _barCode.value = createBarCode(roomId)
                setState { copy(loading = false) }
                setEvent(event = WaitingRoomDataEvent.CreateWaitingRoomSuccess)
                logD(
                    """
                    [fun createWaitingRoom success]
                        _waitingRoomData = ${_waitingRoom.value}
                        _qrCode = ${_barCode.value}
                """.trimIndent()
                )
            }.onFailure {
                setState { copy(loading = false) }
                setEvent(event = WaitingRoomDataEvent.CreateWaitingRoomFailure)
                logD("[fun createWaitingRoom failure]")
            }
        }
    }

    fun getWaitingRoom(roomId: String) {

    }

    fun createBarCode(roomId: String): Bitmap {
        val width = 600
        val height = 300
        val bitMatrix = MultiFormatWriter().encode(
            roomId,
            BarcodeFormat.CODE_128,
            width,
            height,
            null
        )
        val bmp = createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp[x, y] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            }
        }
        return bmp
    }

    fun createWaitingRoome(nickname: String) {
        setState { copy(loading = true) }
        logD(
            """
            [fun createWaitingRoom parameter]
                nickname = $nickname
        """.trimIndent()
        )
        viewModelScope.launch {
            retrofitRepository.createWaitingRoom(
                user = User(
                    userId = UUID.randomUUID().toString(),
                    nickname = nickname
                )
            ).onSuccess {
//                _waitingRoom.value = WaitingRoom(
//                    roomId = TODO(),
//                    hostId = TODO(),
//                    participantList = TODO(),
//                    waitingRoomStatus = TODO()
//                )
//                _barCode.value = createBarCode(roomId)
                setState { copy(loading = false) }
                setEvent(event = WaitingRoomDataEvent.CreateWaitingRoomSuccess)
                logD(
                    """
                        $it
                    [fun createWaitingRoom success]
                        _waitingRoomData = ${_waitingRoom.value}
                        _qrCode = ${_barCode.value}
                """.trimIndent()
                )
            }.onFailure {
                setState { copy(loading = false) }
                setEvent(event = WaitingRoomDataEvent.CreateWaitingRoomFailure)
                logD("[fun createWaitingRoom failure]")
            }
        }
    }


}

data class WaitingRoomDataState(
    val loading: Boolean = false,
)

sealed class WaitingRoomDataEvent {
    object Idle : WaitingRoomDataEvent()
    object CreateWaitingRoomSuccess : WaitingRoomDataEvent()
    object CreateWaitingRoomFailure : WaitingRoomDataEvent()
    class ShowError(val message: String) : WaitingRoomDataEvent()
}
