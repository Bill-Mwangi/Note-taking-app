package com.bill.note_taking_app.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bill.note_taking_app.domain.note.Note
import com.bill.note_taking_app.domain.note.NoteDataSource
import com.bill.note_taking_app.domain.note.SearchNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
  private val noteDataSource: NoteDataSource,
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val searchNote = SearchNote()

  private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
  private val searchText = savedStateHandle.getStateFlow("searchText", "")
  private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

  val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
    NoteListState(
      notes = searchNote.execute(notes, searchText),
      searchText = searchText,
      isSearchActive = isSearchActive
    )
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

  fun loadNotes() {
    viewModelScope.launch {
      savedStateHandle["notes"] = noteDataSource.getAllNotes()
    }
  }

  fun onSearchTextChange(text: String) {
    savedStateHandle["searchText"] = text
  }

  fun onToggleSearch() {
    savedStateHandle["isSearchActive"] = !isSearchActive.value

    if (!isSearchActive.value) savedStateHandle["searchText"] = ""
  }

  fun deleteNoteById(id: Long) {
    viewModelScope.launch {
      noteDataSource.deleteNoteById(id)
      loadNotes()
    }
  }
}