package com.bill.note_taking_app.android.note_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentHintTextField(
  text: String,
  hint: String,
  isHintVisible: Boolean,
  modifier: Modifier = Modifier,
  textStyle: TextStyle = TextStyle(),
  onValueChange: (String) -> Unit,
  onFocusChange: (FocusState) -> Unit,
  singleLine: Boolean = false
) {
  Box(modifier = modifier) {
    BasicTextField(
      value = text,
      onValueChange = onValueChange,
      textStyle = textStyle,
      singleLine = singleLine,
      modifier = Modifier
        .fillMaxWidth()
        .onFocusChanged { focusState ->
          onFocusChange(focusState)
        }
    )

    if (isHintVisible) {
      Text(
        text = hint,
        style = textStyle,
        color = Color.DarkGray
      )
    }
  }
}