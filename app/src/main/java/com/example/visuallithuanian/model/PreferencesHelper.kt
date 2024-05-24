package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val COUNTER_KEY = "counter"
    }

    fun getCounter(): Int {
        return sharedPreferences.getInt(COUNTER_KEY, 0)
    }

    fun incrementCounter() {
        val currentCounter = getCounter()
        sharedPreferences.edit().putInt(COUNTER_KEY, currentCounter + 1).apply()
    }
}
