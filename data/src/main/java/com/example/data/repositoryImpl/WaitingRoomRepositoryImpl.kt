package com.example.data.repositoryImpl

import android.graphics.Bitmap
import com.example.data.api.BlindTrueOrDareApi
import com.example.data.model.GameStartDto
import com.example.data.model.QuestionSettingDto
import com.example.data.model.WaitingRoomDto
import com.example.data.toDomain
import com.example.data.toDto
import com.example.domain.common.ApiResponse
import com.example.domain.common.request
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.CreateWaitingRoomRequest
import com.example.domain.model.Player
import com.example.domain.model.QuestionSetting
import com.example.domain.model.WaitingRoom
import com.example.domain.repository.WaitingRoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Singleton
class WaitingRoomRepositoryImpl(
    private val api: BlindTrueOrDareApi
) : WaitingRoomRepository {

    private val _waitingRoom = MutableStateFlow<WaitingRoom?>(null)
    override val waitingRoom: StateFlow<WaitingRoom?> = _waitingRoom.asStateFlow()

    private val _questionSetting = MutableStateFlow<QuestionSetting?>(null)
    override val questionSetting: StateFlow<QuestionSetting?> = _questionSetting.asStateFlow()

    private val _qrCode = MutableStateFlow<Bitmap?>(null)
    override val qrCode: StateFlow<Bitmap?> = _qrCode.asStateFlow()

    private val _player = MutableStateFlow<Player?>(null)
    override val player: StateFlow<Player?> = _player.asStateFlow()

    override suspend fun createWaitingRoom(
        player: Player,
    ): ApiResponse<CreateWaitingRoom> =
        request {
            api.createWaitingRoom(
                createWaitingRoomRequestDto = CreateWaitingRoomRequest(
                    player = player
                ).toDto()
            ).toDomain()
        }

    override fun setWaitingRoom(data: String?) {
        data?.let {
            _waitingRoom.value = Json.decodeFromString<WaitingRoomDto>(data).toDomain()
        } ?: run {
            _waitingRoom.value = null
        }
    }

    override fun setQrCode(qrCode: Bitmap?) {
        _qrCode.value = qrCode
    }

    override fun setPlayer(player: Player?) {
        _player.value = player
    }

    override fun setQuestionRoomSetting(data: String) {
        _questionSetting.value = Json.decodeFromString<QuestionSettingDto>(data).toDomain()
    }

    override fun setWaitingRoomAndQuestionRoomSettingUseCase(data: String) {
        val message = Json.decodeFromString<GameStartDto>(data).toDomain()
        _waitingRoom.value = message.waitingRoom
        _questionSetting.value = message.questionSetting
    }
}