package com.example.domain.repository

import android.graphics.Bitmap
import com.example.domain.common.ApiResponse
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.Player
import com.example.domain.model.QuestionRoomSetting
import com.example.domain.model.WaitingRoom
import kotlinx.coroutines.flow.StateFlow


interface WaitingRoomRepository {
    val waitingRoom: StateFlow<WaitingRoom?>
    val questionRoomSetting: StateFlow<QuestionRoomSetting?>
    val qrCode: StateFlow<Bitmap?>
    val player: StateFlow<Player?>

    fun setWaitingRoom(data: String?)
    fun setQrCode(qrCode: Bitmap?)

    suspend fun createWaitingRoom(
        player: Player,
    ): ApiResponse<CreateWaitingRoom>
    fun setPlayer(player: Player?)
    fun setQuestionRoomSetting(data: String)
    fun setWaitingRoomAndQuestionRoomSettingUseCase(data: String)
}