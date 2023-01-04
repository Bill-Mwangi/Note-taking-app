package com.bill.note_taking_app.android.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bill.note_taking_app.domain.note.Note
import com.bill.note_taking_app.domain.note.NoteDataSource
import com.bill.note_taking_app.domain.time.DateTimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
  private val noteDataSource: NoteDataSource,
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "")
  private val isNoteTitleFocused = savedStateHandle.getStateFlow(
    "isNoteTitleHintVisible",
    false
  )
  private val noteContent = savedStateHandle.getStateFlow("noteContent", "")
  private val isNoteContentFocused = savedStateHandle.getStateFlow(
    "isNoteContentHintVisible",
    false
  )
  private val noteColour = savedStateHandle.getStateFlow(
    "noteColour",
    Note.generateRandomColour()
  )

  val state = combine(
    noteTitle,
    isNoteTitleFocused,
    noteContent,
    isNoteContentFocused,
    noteColour
  ) { title, isTitleFocused, content, isContentFocused, colour ->
    NoteDetailState(
      noteTitle = title,
      isNoteTitleHintVisible = title.isEmpty() && !isTitleFocused,
      noteContent = content,
      isNoteContentHintVisible = content.isEmpty() && !isContentFocused,
      noteColour = colour
    )
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

  private var _hasNoteBeenSaved = MutableStateFlow(false)
  val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

  private var existingNoteId: Long? = null

  init {
    savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
      if (existingNoteId == -1L) return@let

      this.existingNoteId = existingNoteId

      viewModelScope.launch {
        noteDataSource.getNoteById(existingNoteId)?.let { note ->
          savedStateHandle["noteTitle"] = note.title
          savedStateHandle["noteContent"] = note.content
          savedStateHandle["noteColour"] = note.colourHex
        }
      }
    }
  }

  fun onNoteTitleChange(text: String) {
    savedStateHandle["noteTitle"] = text
  }

  fun onNoteContentChange(text: String) {
    savedStateHandle["noteContent"] = text
  }

  fun onTitleFocusChange(isFocused: Boolean) {
    savedStateHandle["isNoteTitleHintVisible"] = isFocused
  }

  fun onContentFocusChange(isFocused: Boolean) {
    savedStateHandle["isNoteContentHintVisible"] = isFocused
  }

  fun saveNote() {
    viewModelScope.launch {
      noteDataSource.insertNote(
        Note(
          id = existingNoteId,
          title = noteTitle.value,
          content = noteContent.value,
          colourHex = noteColour.value,
          created = DateTimeUtil.now()
        )
      )
      _hasNoteBeenSaved.value = true
    }
  }
}