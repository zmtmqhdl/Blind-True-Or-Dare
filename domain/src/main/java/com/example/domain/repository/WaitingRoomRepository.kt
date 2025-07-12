package com.example.domain.repository

import android.graphics.Bitmap
import com.example.domain.common.ApiResponse
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.Player
import com.example.domain.model.WaitingRoom
import kotlinx.coroutines.flow.StateFlow


interface WaitingRoomRepository {
    val waitingRoom: StateFlow<WaitingRoom?>
    val barCode: StateFlow<Bitmap?>

    fun saveWaitingRoom(waitingRoom: WaitingRoom)
    fun saveBarCode(barcode: Bitmap)


    suspend fun createWaitingRoom(
        player: Player,
    ): ApiResponse<CreateWaitingRoom>
}