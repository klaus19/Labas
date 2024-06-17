package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    companion object {
        private const val COUNTER_KEY = "counter_key"
        private const val DIAMOND_COUNTER_KEY = "diamond_counter_key"
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
}
