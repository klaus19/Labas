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

    fun saveCounterValue(value: Int) {
        with(sharedPreferences.edit()) {
            putInt("counter_value", value)
            apply()
        }
    }

    fun saveCounterValueLearned(value: Int) {
        with(sharedPreferences.edit()) {
            putInt("learned_value", value)
            apply()
        }
    }

    fun loadCounterValue(): Int {
        return sharedPreferences.getInt("counter_value", 0)
    }

    fun loadCounterValueLearned(): Int {
        return sharedPreferences.getInt("learned_value", 0)
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

    fun getSavedItems(): Set<String> {
        return sharedPreferences.getStringSet(SAVED_ITEMS_KEY, setOf()) ?: setOf()
    }

    fun saveSavedItems(items: Set<String>) {
        with(sharedPreferences.edit()) {
            putStringSet(SAVED_ITEMS_KEY, items)
            apply()
        }
    }
}
