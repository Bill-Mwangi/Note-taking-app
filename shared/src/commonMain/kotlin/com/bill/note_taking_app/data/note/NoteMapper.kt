package com.bill.note_taking_app.data.note

import com.bill.note_taking_app.domain.note.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note {
  return Note(
    id = id,
    title = title,
    content = content,
    colourHex = colourHex,
    created = Instant
      .fromEpochMilliseconds(created)
      .toLocalDateTime(TimeZone.currentSystemDefault())
  )
}