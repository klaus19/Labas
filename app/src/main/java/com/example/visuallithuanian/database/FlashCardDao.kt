package com.example.visuallithuanian.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface FlashCardDao {

    @Query("SELECT * FROM flashcards ORDER BY front ASC, back ASC")
    fun getAlphabetizedWords(): Flow<List<FlashcardPair>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(pair: FlashcardPair)

    @Delete
    suspend fun deleteWord(pair:FlashcardPair)

}