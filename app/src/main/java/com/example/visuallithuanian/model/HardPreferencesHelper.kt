package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class HardPreferencesHelper(context: Context) {

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_names",Context.MODE_PRIVATE)

    companion object{
        private val MATHS = "maths"
        private val INTERFACE = "interface"
        private val ANIMALSWORDS = "animal_words"
        private val FEELINGS = "feelings"
        private val RIGHTS = "rights"
        private val ACTIONS = "actions"
        private val ADJECTIVES = "adjectives"
    }

    // MATHS Flashcard
    fun saveProgressMaths(progress: Int){
        with(sharedPreferences.edit()){
            putInt(MATHS,progress)
            apply()
        }
    }
    fun getProgressMath():Int{
        return sharedPreferences.getInt(MATHS,0)
    }

    // Interface Flashcard
    fun saveProgressInterface(progress: Int){
        with(sharedPreferences.edit()){
            putInt(INTERFACE,progress)
            apply()
        }
    }
    fun getProgressInterface():Int{
        return sharedPreferences.getInt(INTERFACE,0)
    }

    // ANIMAL words
    fun saveProgressAnimalWords(progress: Int){
        with(sharedPreferences.edit()){
            putInt(ANIMALSWORDS,progress)
            apply()
        }
    }
    fun getProgressAnimalWords():Int{
        return sharedPreferences.getInt(ANIMALSWORDS,0)
    }

    //FEELINGS
    fun saveProgressFeelings(progress: Int){
        with(sharedPreferences.edit()){
            putInt(FEELINGS,progress)
            apply()
        }
    }
    fun getProgressFeelings():Int{
        return sharedPreferences.getInt(FEELINGS,0)
    }

    //RIGHTS
    fun saveProgressRights(progress: Int){
        with(sharedPreferences.edit()){
            putInt(RIGHTS,progress)
            apply()
        }
    }
    fun getProgressRights():Int{
        return sharedPreferences.getInt(RIGHTS,0)
    }

    // ACTIONS
    fun saveProgressActions(progress: Int){
        with(sharedPreferences.edit()){
            putInt(ACTIONS,progress)
            apply()
        }
    }
    fun getProgressActions():Int{
        return sharedPreferences.getInt(ACTIONS,0)
    }

    //ADJECTIVES
    fun saveProgressAdjectives(progress: Int){
        with(sharedPreferences.edit()){
            putInt(ADJECTIVES,progress)
            apply()
        }
    }
    fun getProgressAdjectives():Int{
        return sharedPreferences.getInt(ADJECTIVES,0)
    }
}