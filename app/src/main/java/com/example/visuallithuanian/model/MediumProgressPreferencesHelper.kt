package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class MediumProgressPreferencesHelper(context: Context) {

    val sharedPreferences:SharedPreferences = context.getSharedPreferences("app_names",Context.MODE_PRIVATE)

    companion object{
        private const val ANIMALS_FLASHCARD ="animals_words"
        private const val BEST_100_FLASH = "100_best_words"
        private const val FOOD = "food_ingredients"
        private const val VEGAN = "vegan_foods"
    }

    // Animals Flashcard
    fun saveProgressAnimalsFlash(progress:Int){
        with(sharedPreferences.edit()){
            putInt(ANIMALS_FLASHCARD,progress)
            apply()
        }
    }

    fun getProgressAnimalsFlash():Int{
        return sharedPreferences.getInt(ANIMALS_FLASHCARD,0)
    }

    //Best 100 words flashcard
    fun saveProgress100BestWords(progress: Int){
        with(sharedPreferences.edit()){
            putInt(BEST_100_FLASH,progress)
            apply()
        }
    }
    fun getProgress100BestWords():Int{
        return sharedPreferences.getInt(BEST_100_FLASH,0)
    }


     // Food and Ingredients
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