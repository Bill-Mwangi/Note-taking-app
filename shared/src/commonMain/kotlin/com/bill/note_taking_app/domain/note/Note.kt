package com.bill.note_taking_app.domain.note

import com.bill.note_taking_app.presentation.*
import kotlinx.datetime.LocalDateTime

data class Note(
  val id: Long,
  val title: String,
  val content: String,
  val colourHex: Long,
  val created: LocalDateTime
) {
  companion object {
    private val colours = listOf(RedOrangeHex, RedPinkHex, BabyBlueHex, LightGreenHex, VioletHex)

    fun generateRandomColour() = colours.random()
  }
}
