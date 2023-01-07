package com.bill.note_taking_app.di

import com.bill.note_taking_app.data.local.DatabaseDriverFactory
import com.bill.note_taking_app.data.note.SqlDelightNoteDataSource
import com.bill.note_taking_app.database.NoteDatabase
import com.bill.note_taking_app.domain.note.NoteDataSource

class DatabaseModule {
  private val factory by lazy { DatabaseDriverFactory() }
  val noteDataSource: NoteDataSource by lazy {
    SqlDelightNoteDataSource(NoteDatabase(factory.createDriver()))
  }
}