package com.example.data

import com.example.data.model.CreateRoomDto
import com.example.data.model.CreateRoomRequestDto
import com.example.data.model.MessageDto
import com.example.data.model.PlayerDto
import com.example.data.model.QuestionDto
import com.example.data.model.RoomDto
import com.example.domain.model.CreateRoom
import com.example.domain.model.CreateRoomRequest
import com.example.domain.model.Message
import com.example.domain.model.Player
import com.example.domain.model.Question
import com.example.domain.model.Room

fun Player.toDto(): PlayerDto =
    PlayerDto(
        playerId = playerId,
        nickname = nickname
    )

fun CreateRoomRequest.toDto() : CreateRoomRequestDto =
    CreateRoomRequestDto(
        player = player.toDto()
    )

fun PlayerDto.toDomain(): Player =
    Player(
        playerId = playerId,
        nickname = nickname
    )

fun CreateRoomDto.toDomain() : CreateRoom =
    CreateRoom(
        roomId = roomId
    )

fun RoomDto.toDomain(): Room =
    Room(
        roomId = roomId,
        hostId = hostId,
        participantList = participantList.map { it.toDomain() }.toSet(),
        roomStatus = roomStatus,
        writeTime = writeTime,
        questionNumber = questionNumber
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
        playerId = playerId,
        data = data,
        timestamp = timestamp
    )

fun QuestionDto.toDomain(): Question =
    Question(
        playerId = playerId,
        question = question,
        oVoters = oVoters,
        xVoters = xVoters
    )

fun Question.toDto(): QuestionDto =
    QuestionDto(
        playerId = playerId,
        question = question,
        oVoters = oVoters,
        xVoters = xVoters
    )