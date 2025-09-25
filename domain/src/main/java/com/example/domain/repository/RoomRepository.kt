package com.example.domain.repository

import android.graphics.Bitmap
import com.example.domain.common.ApiResponse
import com.example.domain.model.Answer
import com.example.domain.model.CreateRoom
import com.example.domain.model.Player
import com.example.domain.model.Question
import com.example.domain.model.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface RoomRepository {
    val room: StateFlow<Room?>
    val qrCode: StateFlow<Bitmap?>
    val player: StateFlow<Player?>
    val myQuestionList: StateFlow<List<Question>>
    val myAnswerList: StateFlow<List<Answer>>

    suspend fun createRoom(player: Player): ApiResponse<CreateRoom>

    fun setRoom(data: String?)
    fun setQrCode(qrCode: Bitmap?)
    fun setPlayer(player: Player?)
    fun setMyQuestionList(myQuestionList: List<Question>)
    fun setMyAnswerList(myAnswerList: List<Answer>)
}
