package com.example.presentation.sharedViewModel

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.data.repositoryImpl.FirebaseRepository
import com.example.presentation.core.ProjectViewModel
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
import com.example.domain.model.Status
import com.example.domain.model.User
import com.example.domain.model.WaitingRoomData
import java.util.UUID

@HiltViewModel
class WaitingRoomDataViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ProjectViewModel(tag = "WaitingRoomDataViewModel") {

    private val _waitingRoomData = MutableStateFlow<WaitingRoomData?>(null)
    val waitingRoomData: Flow<WaitingRoomData?> = _waitingRoomData.asStateFlow()

    private val _barCode = MutableStateFlow<Bitmap?>(null)
    val barCode: Flow<Bitmap?> = _barCode.asStateFlow()


    fun createWaitingRoom(nickname: String) {
        logD("""
            [fun createWaitingRoom parameter]
                nickname = $nickname
        """.trimIndent())
        viewModelScope.launch {
            val roomId = UUID.randomUUID().toString().take(8)
            val userId = UUID.randomUUID().toString()
            firebaseRepository.createWaitingRoom(
                waitingRoomData = WaitingRoomData(
                    roomId = roomId,
                    hostId = userId,
                    participantList = listOf(User(id = userId, nickname = nickname)),
                    status = Status.WAITING,
                )
            ).onSuccess {
                _waitingRoomData.value = it
                _barCode.value = createBarCode(roomId)
                logD("""
                    [fun createWaitingRoom success]
                        _waitingRoomData = ${_waitingRoomData.value}
                        _qrCode = ${_barCode.value}
                """.trimIndent())
            }.onFailure {
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
}