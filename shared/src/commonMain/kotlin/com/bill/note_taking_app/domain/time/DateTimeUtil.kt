package com.bill.note_taking_app.domain.time

import kotlinx.datetime.*

object DateTimeUtil {
  fun now(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
  }

  fun toEpochMillis(dateTime: LocalDateTime): Long {
    return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
  }

  fun formatNoteDate(dateTime: LocalDateTime): String {
    val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
    val day = dateTime.dayOfMonth
    val year = dateTime.year
    val hour = dateTime.hour
    val minute = dateTime.minute
    return buildString {
      append(month)
      append(" ")
      append(day)
      append(" ")
      append(year)
      append(", ")
      append(hour)
      append(":")
      append(minute)
    }
  }
}