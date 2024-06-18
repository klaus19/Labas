package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val COUNTER_KEY = "counter_key"
        private const val DIAMOND_COUNTER_KEY = "diamond_counter_key"
        private const val PROGRESS_KEY = "progress_key"
        private const val CURRENT_TRIPLE_INDEX_KEY = "current_triple_index_key"
        private const val SAVED_FLASHCARDS_KEY = "saved_flashcards_key"
        private const val QUESTIONS_COUNTER_KEY = "questions_counter_key" // New key for QuestionsFragment counter
        private const val SAVED_ITEMS_KEY = "saved_items_key" // Key for saved items set
    }

    fun incrementCounter() {
        val currentCounter = getCounter()
        saveCounter(currentCounter + 1)
    }

    fun decrementCounter() {
        val currentCounter = getCounter()
        if (currentCounter > 0) {
            saveCounter(currentCounter - 1)
        }
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

    fun saveSavedFlashcards(savedFlashcards: Set<String>) {
        with(sharedPreferences.edit()) {
            putStringSet(SAVED_FLASHCARDS_KEY, savedFlashcards)
            apply()
        }
    }

    fun getSavedFlashcards(): Set<String> {
        return sharedPreferences.getStringSet(SAVED_FLASHCARDS_KEY, setOf()) ?: setOf()
    }

    // New methods for QuestionsFragment counter
    fun incrementQuestionsCounter() {
        val currentCounter = getQuestionsCounter()
        saveQuestionsCounter(currentCounter + 1)
    }

    fun decrementQuestionsCounter() {
        val currentCounter = getQuestionsCounter()
        if (currentCounter > 0) {
            saveQuestionsCounter(currentCounter - 1)
        }
    }

    fun saveQuestionsCounter(counter: Int) {
        with(sharedPreferences.edit()) {
            putInt(QUESTIONS_COUNTER_KEY, counter)
            apply()
        }
    }

    fun getQuestionsCounter(): Int {
        return sharedPreferences.getInt(QUESTIONS_COUNTER_KEY, 0)
    }

    fun saveCounterValue(value: Int) {
        with(sharedPreferences.edit()) {
            putInt("counter_value", value)
            apply()
        }
    }

    fun loadCounterValue(): Int {
        return sharedPreferences.getInt("counter_value", 0)
    }

    // New methods for managing saved items
    fun addSavedItem(item: String) {
        val savedItems = getSavedItems().toMutableSet()
        savedItems.add(item)
        saveSavedItems(savedItems)
    }

    fun isItemSaved(item: String): Boolean {
        return getSavedItems().contains(item)
    }

    private fun getSavedItems(): Set<String> {
        return sharedPreferences.getStringSet(SAVED_ITEMS_KEY, setOf()) ?: setOf()
    }

    private fun saveSavedItems(items: Set<String>) {
        with(sharedPreferences.edit()) {
            putStringSet(SAVED_ITEMS_KEY, items)
            apply()
        }
    }
}
