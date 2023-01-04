package com.bill.note_taking_app.domain.note

import com.bill.note_taking_app.domain.time.DateTimeUtil

class SearchNote {
  fun execute(notes: List<Note>, query: String): List<Note> {
    if (query.isBlank()) return notes
    return notes.filter {
      it.title.trim().lowercase().contains(query.lowercase()) || it.content.trim().lowercase()
        .contains(query.lowercase())
    }.sortedBy {
      DateTimeUtil.toEpochMillis(it.created)
    }
  }
}