package com.bill.note_taking_app.android.note_detail

data class NoteDetailState(
  val noteTitle: String = "",
  val noteContent: String = "",
  val noteColour: Long = 0xffffffff,
  val isNoteTitleHintVisible: Boolean = false,
  val isNoteContentHintVisible: Boolean = false
)
