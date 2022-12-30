package com.bill.note_taking_app

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform