package com.example.data

import com.example.data.model.AnswerDto
import com.example.data.model.CreateRoomDto
import com.example.data.model.CreateRoomRequestDto
import com.example.data.model.MessageDto
import com.example.data.model.PlayerDto
import com.example.data.model.QuestionDto
import com.example.data.model.RoomDto
import com.example.domain.model.Answer
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
        host = host.toDomain(),
        participantList = participantList.map { it.toDomain() }.toSet(),
        roomStatus = roomStatus,
        writeTime = writeTime,
        questionNumber = questionNumber,
        questionList = questionList.map { it.toDomain()}
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
        player = player?.toDto(),
        data = data,
        timestamp = timestamp
    )

fun QuestionDto.toDomain(): Question =
    Question(
        questionId = questionId,
        player = player.toDomain(),
        question = question,
        oVoter = oVoter.map { it.toDomain() }.toSet(),
        xVoter = xVoter.map { it.toDomain() }.toSet(),
        skipper = skipper.map { it.toDomain() }.toSet()
    )

fun Question.toDto(): QuestionDto =
    QuestionDto(
        questionId = questionId,
        player = player.toDto(),
        question = question,
        oVoter = oVoter.map { it.toDto() }.toSet(),
        xVoter = xVoter.map { it.toDto() }.toSet(),
        skipper = skipper.map { it.toDto() }.toSet()
    )

fun Answer.toDto(): AnswerDto =
    AnswerDto(
        questionId = questionId,
        player = player.toDto(),
        answer = answer
    )