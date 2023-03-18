package com.example.visuallithuanian.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flashcards")
data class Flashcards(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val front:String,
    val back:String,
)
