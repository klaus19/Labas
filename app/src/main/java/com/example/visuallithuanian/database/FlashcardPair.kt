package com.example.visuallithuanian.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class FlashcardPair(
    @PrimaryKey
    val front:String,
    val back:String,
    val imageSrc:Int,
    val voiceclip:Int,
    var nextDisplayTime: Long = 0L
)
