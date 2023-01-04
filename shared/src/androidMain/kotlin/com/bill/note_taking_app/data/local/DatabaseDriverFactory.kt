package com.bill.note_taking_app.data.local

import android.content.Context
import com.bill.note_taking_app.database.NoteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
  actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(NoteDatabase.Schema, context, "note.db")
  }
}