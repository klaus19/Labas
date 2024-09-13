package com.example.visuallithuanian.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.visuallithuanian.database.FlashcardPair
import com.google.gson.Gson

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

    fun saveNextDisplayTime(cardPair: FlashcardPair, nextDisplayTime: Long) {
        sharedPreferences.edit().apply {
            putLong("${cardPair.front}_nextDisplayTime", nextDisplayTime)
            apply()
        }
    }

    fun getNextDisplayTime(cardPair: FlashcardPair): Long {
        return sharedPreferences.getLong("${cardPair.front}_nextDisplayTime", 0)
    }

    fun incrementCounter() {
        val currentCounter = getCounter()
        saveCounter(currentCounter + 1)
    }

    fun incrementPurpleCount(){
        val currentCounter = getPurpleCounter()
        savePurpleCounter(currentCounter+1)
    }

    fun incrementRedCount(){
        val currentCounter= getRedCounter()
        saveRedCounter(currentCounter+1)
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


    private fun savePurpleCounter(diamondCounter: Int) {
        with(sharedPreferences.edit()) {
            putInt(PURPLE_COUNTER_KEY, diamondCounter)
            apply()
        }
    }

    fun getPurpleCounter(): Int {
        return sharedPreferences.getInt(PURPLE_COUNTER_KEY, 0)
    }

    private fun saveRedCounter(redCounter:Int){
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

    private fun getSavedItems(): Set<String> {
        return sharedPreferences.getStringSet(SAVED_ITEMS_KEY, setOf()) ?: setOf()
    }

    private fun saveSavedItems(items: Set<String>) {
        with(sharedPreferences.edit()) {
            putStringSet(SAVED_ITEMS_KEY, items)
            apply()
        }
    }


    fun addSavedItemCard(item: FlashcardPair) {
        val sharedPrefs = context.getSharedPreferences("learned_items_prefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        // Convert the FlashcardPair object to a suitable format for storing
        val json = Gson().toJson(item)
        val savedItems = sharedPrefs.getStringSet("saved_items", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        // Add the new item
        savedItems.add(json)

        // Save the updated set
        editor.putStringSet("saved_items", savedItems)
        editor.apply()
    }


    // Get saved items
    fun getSavedItemsCardPair(): List<FlashcardPair> {
        val sharedPrefs = context.getSharedPreferences("learned_items_prefs", Context.MODE_PRIVATE)
        val savedItems = sharedPrefs.getStringSet("saved_items", mutableSetOf()) ?: setOf()
        return savedItems.mapNotNull {
            // Convert JSON back to FlashcardPair object
            Gson().fromJson(it, FlashcardPair::class.java)
        }
    }

    fun addSavedItemCardToLearn(item: FlashcardPair){
        val sharedPrefs = context.getSharedPreferences("to_learn_items_prefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        // Convert the FlashcardPair object to a suitable format for storing
        val json = Gson().toJson(item)
        val savedItems = sharedPrefs.getStringSet("saved_items_tolearn", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        // Add the new item
        savedItems.add(json)

        // Save the updated set
        editor.putStringSet("saved_items_tolearn", savedItems)
        editor.apply()
    }

    fun getSavedItemCardPairToLearn():List<FlashcardPair>{
        val sharedPrefs = context.getSharedPreferences("to_learn_items_prefs", Context.MODE_PRIVATE)
        val savedItems = sharedPrefs.getStringSet("saved_items_tolearn", mutableSetOf()) ?: setOf()
        return savedItems.mapNotNull {
            // Convert JSON back to FlashcardPair object
            Gson().fromJson(it, FlashcardPair::class.java)
        }
    }

}
