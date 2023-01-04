package com.bill.note_taking_app.android.note_list

import com.bill.note_taking_app.domain.note.Note

data class NoteListState(
  val notes: List<Note> = emptyList(),
  val searchText: String = "",
  val isSearchActive: Boolean = false
)