package com.bill.note_taking_app.android.note_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bill.note_taking_app.domain.note.Note
import com.bill.note_taking_app.domain.time.DateTimeUtil

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
  viewModel: NoteListViewModel = hiltViewModel(),
  navController: NavController
) {
  val state by viewModel.state.collectAsState()

  LaunchedEffect(key1 = true) {
    viewModel.loadNotes()
  }

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = { navController.navigate("note_detail/-1L") },
        backgroundColor = Color.Black
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "New note",
          tint = Color.White
        )
      }
    }) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth(),
        contentAlignment = Alignment.Center
      ) {
        HideableSearchTextField(
          text = state.searchText,
          isSearchActive = state.isSearchActive,
          onTextChange = viewModel::onSearchTextChange,
          onSearchClick = viewModel::onToggleSearch,
          onCloseClick = viewModel::onToggleSearch,
          modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
        )

        this@Column.AnimatedVisibility(
          visible = !state.isSearchActive,
          enter = fadeIn(),
          exit = fadeOut()
        ) {
          Text(text = "All notes", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        }
      }

      LazyColumn(modifier = Modifier.weight(1f)) {
        items(state.notes,
          key = {
            it.id!!
          }) { note ->
          NoteItem(
            note = note,
            backgroundColour = Color(note.colourHex),
            onNoteClick = { navController.navigate("note_detail/${note.id}") },
            onDeleteClick = { viewModel.deleteNoteById(note.id!!) },
            modifier = Modifier
              .padding(8.dp)
              .animateItemPlacement()
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun NoteListPreview() {
  val isSearchActive = true

  val notes = listOf(
    Note(
      id = 1,
      title = "Android Note 1",
      content = "Sample android note",
      colourHex = 0xffffab91,
      created = DateTimeUtil.now()
    ), Note(
      id = 2,
      title = "Android Note 2",
      content = "Sample android note",
      colourHex = 0xfff48fb1,
      created = DateTimeUtil.now()
    ), Note(
      id = 3,
      title = "Android Note 3",
      content = "Sample android note",
      colourHex = 0xff81deea,
      created = DateTimeUtil.now()
    ), Note(
      id = 4,
      title = "Android Note 4",
      content = "Sample android note",
      colourHex = 0xffcf94da,
      created = DateTimeUtil.now()
    ), Note(
      id = 5,
      title = "Android Note 5",
      content = "Sample android note",
      colourHex = 0xffe7ed9b,
      created = DateTimeUtil.now()
    )
  )

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        onClick = { },
        backgroundColor = Color.Black
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "New note",
          tint = Color.White
        )
      }
    }) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth(),
        contentAlignment = Alignment.Center
      ) {
        HideableSearchTextField(
          text = "Search",
          isSearchActive = isSearchActive,
          onTextChange = { },
          onSearchClick = { },
          onCloseClick = { },
          modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
        )

        this@Column.AnimatedVisibility(
          visible = !isSearchActive,
          enter = fadeIn(),
          exit = fadeOut()
        ) {
          Text(text = "All notes", fontWeight = FontWeight.Bold, fontSize = 30.sp)
        }
      }

      LazyColumn(modifier = Modifier.weight(1f)) {
        items(notes,
          key = {
            it.id!!
          }) { note ->
          NoteItem(
            note = note,
            backgroundColour = Color(note.colourHex),
            onNoteClick = { },
            onDeleteClick = { },
            modifier = Modifier
              .padding(8.dp)
              .animateItemPlacement()
          )
        }
      }
    }
  }
}
