package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class MediumProgressPreferencesHelper(private val context: Context) {

    val sharedPreferences:SharedPreferences = context.getSharedPreferences("app_names",Context.MODE_PRIVATE)

    companion object{
        private const val ANIMALS_FLASHCARD ="animals_words"
        private const val BEST_100_FLASH = "100_best_words"
        private const val FOOD = "food_ingredients"
        private const val VEGAN = "vegan_foods"
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


    fun saveProgressFood(progress: Int){
        with(sharedPreferences.edit()){
            putInt(FOOD,progress)
            apply()
        }
    }
    fun getProgressFood():Int{
        return sharedPreferences.getInt(FOOD,0)
    }

    // Vegan Flash card

    fun savedProgressVegan(progress: Int){
        with(sharedPreferences.edit()){
            putInt(VEGAN,progress)
            apply()
        }
    }

    fun getProgressvegan():Int{
        return sharedPreferences.getInt(VEGAN,0)
    }







}