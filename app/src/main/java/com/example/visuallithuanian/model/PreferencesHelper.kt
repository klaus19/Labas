package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val COUNTER_KEY = "counter_key"
        private const val DIAMOND_COUNTER_KEY = "diamond_counter_key"
        private const val PROGRESS_KEY = "progress_key"
        private const val CURRENT_TRIPLE_INDEX_KEY = "current_triple_index_key"
        private const val SAVED_FLASHCARDS_KEY = "saved_flashcards_key"
    }

    fun incrementCounter() {
        val currentCounter = getCounter()
        saveCounter(currentCounter + 1)
    }

    fun saveCounter(counter: Int) {
        with(sharedPreferences.edit()) {
            putInt(COUNTER_KEY, counter)
            apply()
        }
    }

    fun getCounter(): Int {
        return sharedPreferences.getInt(COUNTER_KEY, 0)
    }

    fun saveDiamondCounter(diamondCounter: Int) {
        with(sharedPreferences.edit()) {
            putInt(DIAMOND_COUNTER_KEY, diamondCounter)
            apply()
        }
    }

    fun getDiamondCounter(): Int {
        return sharedPreferences.getInt(DIAMOND_COUNTER_KEY, 0)
    }

    fun saveProgress(progress: Int) {
        with(sharedPreferences.edit()) {
            putInt(PROGRESS_KEY, progress)
            apply()
        }
    }

    fun getProgress(): Int {
        return sharedPreferences.getInt(PROGRESS_KEY, 0)
    }

    fun saveCurrentTripleIndex(index: Int) {
        with(sharedPreferences.edit()) {
            putInt(CURRENT_TRIPLE_INDEX_KEY, index)
            apply()
        }
    }

    fun getCurrentTripleIndex(): Int {
        return sharedPreferences.getInt(CURRENT_TRIPLE_INDEX_KEY, 0)
    }

    // Method to save the set of saved flashcards
    fun saveSavedFlashcards(savedFlashcards: Set<String>) {
        with(sharedPreferences.edit()) {
            putStringSet(SAVED_FLASHCARDS_KEY, savedFlashcards)
            apply()
        }
    }

    // Method to retrieve the set of saved flashcards
    fun getSavedFlashcards(): Set<String> {
        return sharedPreferences.getStringSet(SAVED_FLASHCARDS_KEY, setOf()) ?: setOf()
    }
}
