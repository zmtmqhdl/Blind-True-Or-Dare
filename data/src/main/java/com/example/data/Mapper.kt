package com.example.data

import com.example.data.model.CreateWaitingRoomDto
import com.example.data.model.CreateWaitingRoomRequestDto
import com.example.data.model.GameStartDto
import com.example.data.model.MessageDto
import com.example.data.model.PlayerDto
import com.example.data.model.QuestionRoomSettingDto
import com.example.data.model.WaitingRoomDataDto
import com.example.data.model.WaitingRoomDto
import com.example.domain.model.CreateWaitingRoom
import com.example.domain.model.CreateWaitingRoomRequest
import com.example.domain.model.GameStart
import com.example.domain.model.Message
import com.example.domain.model.Player
import com.example.domain.model.QuestionRoomSetting
import com.example.domain.model.WaitingRoom
import com.example.domain.model.WaitingRoomStatus

fun Player.toDto(): PlayerDto =
    PlayerDto(
        playerId = playerId,
        nickname = nickname
    )

fun CreateWaitingRoomRequest.toDto() : CreateWaitingRoomRequestDto =
    CreateWaitingRoomRequestDto(
        player = player.toDto()
    )

fun PlayerDto.toDomain(): Player =
    Player(
        playerId = this@toDomain.playerId,
        nickname = nickname
    )

fun CreateWaitingRoomDto.toDomain() : CreateWaitingRoom =
    CreateWaitingRoom(
        waitingRoomId = waitingRoomId
    )



fun WaitingRoomDataDto.toDomain(): WaitingRoom =
    WaitingRoom(
        waitingRoomId = waitingRoomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() },
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )

fun WaitingRoom.toDto(): WaitingRoomDataDto =
    WaitingRoomDataDto(
        waitingRoomId = waitingRoomId,
        hostId = hostId,
        participantList = participantList.map { it.toDto()},
        waitingRoomStatus = WaitingRoomStatus.Waiting
    )

fun WaitingRoomDto.toDomain(): WaitingRoom =
    WaitingRoom(
        waitingRoomId = waitingRoomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() },
        waitingRoomStatus = waitingRoomStatus
    )

fun MessageDto.toDomain(): Message =
    Message(
        type = type,
        data = data,
        timestamp = timestamp
    )

fun Message.toDto(): MessageDto =
    MessageDto(
        type = type,
        senderId = senderId,
        data = data,
        timestamp = timestamp
    )

fun QuestionRoomSettingDto.toDomain(): QuestionRoomSetting =
    QuestionRoomSetting(
        time = time,
        number = number
    )

fun GameStartDto.toDomain(): GameStart =
    GameStart(
        waitingRoom = waitingRoom.toDomain(),
        questionRoomSetting = questionRoomSetting.toDomain()
    )
