package com.example.project1.util

import java.time.ZoneId
import java.time.ZonedDateTime


fun timeToTimestamp(dateTime: String) {

}

fun timestampToTime(dateTime: String) {

}

fun getCurrentUnixTimestamp(): Long {
    val utcZoneId = ZoneId.of("UTC")
    val currentDateTime = ZonedDateTime.now(utcZoneId)
    val instant = currentDateTime.toInstant()
    return instant.epochSecond
}