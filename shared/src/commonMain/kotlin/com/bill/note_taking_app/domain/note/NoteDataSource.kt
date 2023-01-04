package com.bill.note_taking_app.domain.note

interface NoteDataSource {
  suspend fun inserteNote(note: Note)
  suspend fun getNoteById(id: Long): Note?
  suspend fun getAllNotes(): List<Note>
  suspend fun deleteNoteById(id: Long)
}