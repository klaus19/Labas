package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(private val context: Context) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val COUNTER_KEY = "counter_key"
        private const val PURPLE_COUNTER_KEY = "purple_counter_key"
        private const val RED_COUNTER_KEY = "red_counter_key"
        private const val BEST_WORDS = "progress_key"
        private const val SAVED_ITEMS_KEY = "saved_items_key" // Key for saved items set
    }

    fun incrementCounter() {
        val currentCounter = getCounter()
        saveCounter(currentCounter + 1)
    }

    fun incrementPurpleCount(){
        val currentCounter = getPurpleCounter()
        savePurpleCounter(currentCounter+1)
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


    fun savePurpleCounter(diamondCounter: Int) {
        with(sharedPreferences.edit()) {
            putInt(PURPLE_COUNTER_KEY, diamondCounter)
            apply()
        }
    }

    fun getPurpleCounter(): Int {
        return sharedPreferences.getInt(PURPLE_COUNTER_KEY, 0)
    }

    fun saveRedCounter(redCounter:Int){
        with(sharedPreferences.edit()) {
            putInt(RED_COUNTER_KEY, redCounter)
            apply()
        }
    }

    fun getRedCounter(): Int {
        return sharedPreferences.getInt(RED_COUNTER_KEY, 0)
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
