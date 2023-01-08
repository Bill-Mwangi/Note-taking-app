package com.bill.note_taking_app.android.note_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bill.note_taking_app.domain.note.Note
import com.bill.note_taking_app.domain.time.DateTimeUtil


@Composable
fun NoteItem(
  note: Note,
  backgroundColour: Color,
  onNoteClick: () -> Unit,
  onDeleteClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val formattedDate = remember(note.created) {
    DateTimeUtil.formatNoteDate(note.created)
  }

  Column(
    modifier = modifier
      .clip(RoundedCornerShape(5.dp))
      .clickable { onNoteClick() }
      .background(backgroundColour)
      .padding(start = 8.dp, end = 8.dp)
  ) {
    Row(
      modifier = modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = note.title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
      )
      Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Delete note",
        modifier = modifier.clickable(MutableInteractionSource(), null) {
          onDeleteClick()
        }
      )
    }
    Spacer(modifier = modifier.height(16.dp))
    Text(text = note.content, fontWeight = FontWeight.Light)
    Spacer(modifier = modifier.height(16.dp))
    Text(
      text = formattedDate,
      color = Color.DarkGray,
      modifier = Modifier.align(Alignment.End)
    )
  }
}

@Preview
@Composable
fun NoteItemPreview(modifier: Modifier = Modifier) {
  val note = Note(
    id = null,
    title = "Android Note",
    content = "Sample android note",
    colourHex = 0xffe7ed9b,
    created = DateTimeUtil.now()
  )

  Column(
    modifier = modifier
      .clip(RoundedCornerShape(5.dp))
      .clickable { }
      .background(Color(note.colourHex))
      .padding(start = 8.dp, end = 8.dp)
  ) {
    Row(
      modifier = modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = note.title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
      )
      Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Delete note",
        modifier = modifier.clickable(MutableInteractionSource(), null) {

        }
      )
    }
    Spacer(modifier = modifier.height(16.dp))
    Text(text = note.content, fontWeight = FontWeight.Light)
    Spacer(modifier = modifier.height(16.dp))
    Text(
      text = DateTimeUtil.formatNoteDate(note.created),
      color = Color.DarkGray,
      modifier = Modifier.align(Alignment.End)
    )
  }
}