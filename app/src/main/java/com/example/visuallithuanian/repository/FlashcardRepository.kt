package com.example.visuallithuanian.repository

import androidx.annotation.WorkerThread
import com.example.visuallithuanian.database.FlashCardDao
import com.example.visuallithuanian.database.FlashcardPair
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlashcardRepository(private val flashCardDao: FlashCardDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<FlashcardPair>> = flashCardDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(pair: FlashcardPair) {
        flashCardDao.insertWord(pair)
    }

    @WorkerThread
    suspend fun deleteCard(pair:FlashcardPair){
        flashCardDao.deleteWord(pair)
    }
}