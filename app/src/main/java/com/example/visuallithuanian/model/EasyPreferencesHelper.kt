package com.example.visuallithuanian.model

import android.content.Context
import android.content.SharedPreferences

class EasyPreferencesHelper(context: Context) {

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_names",Context.MODE_PRIVATE)

    companion object{
        private const val POINTERS = "pointers"
        private const val QUESTIONS = "questions"
        private const val DAILY = "daily"
        private const val BASIC = "basic"
        private const val HOLIDAYS = "holiday"
        private const val FAMILY = "family"
        private const val KEY = "key_phrases"
        private const val DAY_MONTH = "day_month"
        private const val COLOURS = "colors"
        private const val VERBS = "verbss"
        private const val WORK_PLACE = "work_place"
        private const val NATURE = "nature_flashcard"
        private const val ROMANTIC = "romantic_phrases"

    }

    // Pointers Flashcard
    fun saveProgressPointers(progress: Int){
        with(sharedPreferences.edit()){
            putInt(POINTERS,progress)
            apply()
        }
    }
    fun getProgressPointer():Int{
        return sharedPreferences.getInt(POINTERS,0)
    }


    // Questions Flashcard
    fun saveProgressQuestions(progress: Int){
        with(sharedPreferences.edit()){
            putInt(QUESTIONS,progress)
            apply()
        }
    }
    fun getProgressQuestions():Int{
        return sharedPreferences.getInt(QUESTIONS,0)
    }

    //DAILY BASICS
    fun saveProgressDailyBasics(progress: Int){
        with(sharedPreferences.edit()){
            putInt(DAILY,progress)
            apply()
        }
    }
    fun getProgressDailyBasics():Int{
        return sharedPreferences.getInt(DAILY,0)
    }

    //Basic Actions
    fun saveProgressBasicActions(progress: Int){
        with(sharedPreferences.edit()){
            putInt(BASIC,progress)
            apply()
        }
    }
    fun getProgressBasicActions():Int{
        return sharedPreferences.getInt(BASIC,0)
    }


    // Holidays
    fun saveProgressHolidays(progress: Int){
        with(sharedPreferences.edit()){
            putInt(HOLIDAYS,progress)
            apply()
        }
    }
    fun getProgressHolidays():Int{
        return sharedPreferences.getInt(HOLIDAYS,0)
    }


    // Family
    fun saveProgressFamily(progress: Int){
        with(sharedPreferences.edit()){
            putInt(FAMILY,progress)
            apply()
        }
    }
    fun getProgressFamily():Int{
        return sharedPreferences.getInt(FAMILY,0)
    }

    //Key Phrases
    fun saveProgressKeyPhrases(progress: Int){
        with(sharedPreferences.edit()){
            putInt(KEY,progress)
            apply()
        }
    }
    fun getProgressKeyPhrases():Int{
        return sharedPreferences.getInt(KEY,0)
    }

    // DAYS and MONTH
    fun saveProgressDaysMonths(progress: Int){
        with(sharedPreferences.edit()){
            putInt(DAY_MONTH,progress)
            apply()
        }
    }

    fun getProgressDaysMonths():Int{
        return sharedPreferences.getInt(DAY_MONTH,0)
    }

    //COLORS AND SHAPES

    fun saveProgressColors(progress: Int){
        with(sharedPreferences.edit()){
            putInt(COLOURS,progress)
            apply()
        }
    }

    fun getProgressColors():Int{
        return sharedPreferences.getInt(COLOURS,0)
    }

    // I Verbs

    fun saveProgressVerbs(progress: Int){
        with(sharedPreferences.edit()){
            putInt(VERBS,progress)
            apply()
        }
    }

    fun getProgressVerbs():Int{
        return sharedPreferences.getInt(VERBS,0)
    }

    // Workplace language
    fun saveProgressWorkplacelanguage(progress: Int){
        with(sharedPreferences.edit()){
            putInt(WORK_PLACE,progress)
            apply()
        }
    }

    fun getProgressWorkplacelanaguage():Int{
        return sharedPreferences.getInt(WORK_PLACE,0)
    }

    //Nature

    fun saveProgressNature(progress: Int){
        with(sharedPreferences.edit()){
            putInt(NATURE,progress)
            apply()
        }
    }

    fun getProgressNature():Int{
        return sharedPreferences.getInt(NATURE,0)
    }

    //Romantic Phrases

    fun saveProgressRomanticPhrases(progress: Int){
        with(sharedPreferences.edit()){
            putInt(ROMANTIC,progress)
            apply()
        }
    }

    fun getProgressRomanticPhrases():Int{
        return sharedPreferences.getInt(ROMANTIC,0)
    }



}