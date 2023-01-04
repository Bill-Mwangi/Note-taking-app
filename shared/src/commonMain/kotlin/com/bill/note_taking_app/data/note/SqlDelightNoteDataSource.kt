package com.bill.note_taking_app.data.note

import com.bill.note_taking_app.database.NoteDatabase
import com.bill.note_taking_app.domain.note.Note
import com.bill.note_taking_app.domain.note.NoteDataSource
import com.bill.note_taking_app.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(db: NoteDatabase) : NoteDataSource {
  private val queries = db.noteQueries

  override suspend fun inserteNote(note: Note) {
    queries.insertNote(
      id = note.id,
      title = note.title,
      content = note.content,
      colourHex = note.colourHex,
      created = DateTimeUtil.toEpochMillis(note.created)
    )
  }

  override suspend fun getNoteById(id: Long): Note? {
    return queries
      .getNoteById(id)
      .executeAsOneOrNull()
      ?.toNote()
  }

  override suspend fun getAllNotes(): List<Note> {
    return queries
      .getAllNotes()
      .executeAsList()
      .map { it.toNote() }
  }

  override suspend fun deleteNoteById(id: Long) {
    queries.deleteNoteById(id)
  }
}