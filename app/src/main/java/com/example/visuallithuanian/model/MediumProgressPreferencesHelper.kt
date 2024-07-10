package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class MediumProgressPreferencesHelper(private val context: Context) {

    val sharedPreferences:SharedPreferences = context.getSharedPreferences("app_names",Context.MODE_PRIVATE)

    companion object{
        private const val ANIMALS_FLASHCARD ="animals_words"
        private const val BEST_100_FLASH = "100_best_words"
    }

    fun saveProgressAnimalsFlash(progress:Int){
        with(sharedPreferences.edit()){
            putInt(ANIMALS_FLASHCARD,progress)
            apply()
        }
    }

    fun getProgressAnimalsFlash():Int{
        return sharedPreferences.getInt(ANIMALS_FLASHCARD,0)
    }

    fun saveProgress100BestWords(progress: Int){
        with(sharedPreferences.edit()){
            putInt(BEST_100_FLASH,progress)
            apply()
        }
    }
    fun getProgress100BestWords():Int{
        return sharedPreferences.getInt(BEST_100_FLASH,0)
    }






}