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
        private const val COMP = "computer"
        private const val VILLAGE = "village"
        private const val TIME  = "time"
        private const val CINEMA = "cinema"
        private const val NUMBERS = "number"
        private const val BUSINESS = "business_language"
        private const val CAFE = "cafe"
        private const val SPORT = "sports"
        private const val THINGS = "thing"
        private const val PERSONALITY = "person"
        private const val PROFESSION = "professions"
        private const val HOUSEHOLD = "house"
        private const val WEEK = "weekly_basics"


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
    // Computer Technology
    fun savedProgressComputer(progress: Int){
        with(sharedPreferences.edit()){
            putInt(COMP,progress)
            apply()
        }
    }

    fun getProgressComputer():Int{
        return sharedPreferences.getInt(COMP,0)
    }

    // Towns and villages
    fun savedProgressVillage(progress: Int){
        with(sharedPreferences.edit()){
            putInt(VILLAGE,progress)
            apply()
        }
    }

    fun getProgressVillage():Int{
        return sharedPreferences.getInt(VILLAGE,0)
    }

    // Time
    fun savedProgressTime(progress: Int){
        with(sharedPreferences.edit()){
            putInt(TIME,progress)
            apply()
        }
    }

    fun getProgressTime():Int{
        return sharedPreferences.getInt(TIME,0)
    }

    //Cinema
    fun savedProgressCinema(progress: Int){
        with(sharedPreferences.edit()){
            putInt(CINEMA,progress)
            apply()
        }
    }

    fun getProgressCinema():Int{
        return sharedPreferences.getInt(CINEMA,0)
    }

    //Numbers
    fun savedProgressNumbers(progress: Int){
        with(sharedPreferences.edit()){
            putInt(NUMBERS,progress)
            apply()
        }
    }

    fun getProgressNumbers():Int{
        return sharedPreferences.getInt(NUMBERS,0)
    }

    //BUSINESS
    fun savedProgressBusiness(progress: Int){
        with(sharedPreferences.edit()){
            putInt(BUSINESS,progress)
            apply()
        }
    }

    fun getProgressBusiness():Int{
        return sharedPreferences.getInt(BUSINESS,0)
    }

    //CAFE
    fun savedProgressCafe(progress: Int){
        with(sharedPreferences.edit()){
            putInt(CAFE,progress)
            apply()
        }
    }
    fun getProgressCafe():Int{
        return sharedPreferences.getInt(CAFE,0)
    }

    //sports
    fun savedProgressSports(progress: Int){
        with(sharedPreferences.edit()){
            putInt(SPORT,progress)
            apply()
        }
    }

    fun getProgressSports():Int{
        return sharedPreferences.getInt(SPORT,0)
    }

    //Things
    fun savedProgressThings(progress: Int){
        with(sharedPreferences.edit()){
            putInt(THINGS,progress)
            apply()
        }
    }

    fun getProgressThings():Int{
        return sharedPreferences.getInt(THINGS,0)
    }

    //PERSONALITY
    fun savedProgressPersonality(progress: Int){
        with(sharedPreferences.edit()){
            putInt(PERSONALITY,progress)
            apply()
        }
    }

    fun getProgressPersonality():Int{
        return sharedPreferences.getInt(PERSONALITY,0)
    }

    //Professions
    fun savedProgressProfessions(progress: Int){
        with(sharedPreferences.edit()){
            putInt(PROFESSION,progress)
            apply()
        }
    }

    fun getProgressProfession():Int{
        return sharedPreferences.getInt(PROFESSION,0)
    }

    // HouseHold
    fun savedProgressHouseHold(progress: Int){
        with(sharedPreferences.edit()){
            putInt(HOUSEHOLD,progress)
            apply()
        }
    }

    fun getProgressHouseHold():Int{
        return sharedPreferences.getInt(HOUSEHOLD,0)
    }

    // Weekly basics

    fun savedProgressWeeklyBasics(progress: Int){
        with(sharedPreferences.edit()){
            putInt(WEEK,progress)
            apply()
        }
    }

    fun getProgressWeeklyBasics():Int{
        return sharedPreferences.getInt(WEEK,0)
    }


}