package com.bill.note_taking_app.data.local

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
  fun createDriver(): SqlDriver
}