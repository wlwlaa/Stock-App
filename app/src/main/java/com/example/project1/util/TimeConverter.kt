package com.example.project1.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime


fun timestampToTime(dateTime: Long): LocalDateTime {
    val instant = Instant.ofEpochSecond(dateTime)
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
}

fun getCurrentUnixTimestamp(): Long {
    val utcZoneId = ZoneId.of("UTC")
    val currentDateTime = ZonedDateTime.now(utcZoneId)
    val instant = currentDateTime.toInstant()
    return instant.epochSecond
}