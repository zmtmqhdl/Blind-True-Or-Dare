package com.example.data.repositoryImpl

import android.graphics.Bitmap
import com.example.data.api.BlindTrueOrDareApi
import com.example.data.common.request
import com.example.data.model.RoomDto
import com.example.data.toDomain
import com.example.data.toDto
import com.example.domain.common.ApiResponse
import com.example.domain.model.Answer
import com.example.domain.model.CreateRoom
import com.example.domain.model.CreateRoomRequest
import com.example.domain.model.Player
import com.example.domain.model.Question
import com.example.domain.model.Room
import com.example.domain.repository.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepositoryImpl @Inject constructor(
    private val api: BlindTrueOrDareApi
) : RoomRepository {

    private val _room = MutableStateFlow<Room?>(null)
    override val room: StateFlow<Room?> = _room

    private val _qrCode = MutableStateFlow<Bitmap?>(null)
    override val qrCode: StateFlow<Bitmap?> = _qrCode

    private val _player = MutableStateFlow<Player?>(null)
    override val player: StateFlow<Player?> = _player

    private val _myQuestionList = MutableStateFlow<List<Question>>(emptyList())
    override val myQuestionList: StateFlow<List<Question>> = _myQuestionList

    private val _myAnswerList = MutableStateFlow<List<Answer>>(emptyList())
    override val myAnswerList: StateFlow<List<Answer>> = _myAnswerList

    override suspend fun createRoom(player: Player): ApiResponse<CreateRoom> =
        request {
            api.createRoom(CreateRoomRequest(player).toDto()).toDomain()
        }

    override fun setRoom(data: String?) {
        _room.value = data?.let { Json.decodeFromString<RoomDto>(it).toDomain() }
    }

    override fun setQrCode(qrCode: Bitmap?) { _qrCode.value = qrCode }
    override fun setPlayer(player: Player?) { _player.value = player }
    override fun setMyQuestionList(myQuestionList: List<Question>) { _myQuestionList.value = myQuestionList }
    override fun setMyAnswerList(myAnswerList: List<Answer>) { _myAnswerList.value = myAnswerList }
}
