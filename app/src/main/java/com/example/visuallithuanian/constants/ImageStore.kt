package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ImageStore {
    val imagesNamesMap = mutableMapOf<Int, Pair<String, String>>()

    fun addImageResource(resId:Int,english:String,lithuanian:String){
        imagesNamesMap[resId] = Pair(english,lithuanian)
    }

    fun removeImageResource(resId: Int){
        imagesNamesMap.remove(resId)
    }
    fun getRandomPairs(count: Int): List<Pair<Int, Pair<String, String>>> {
        return imagesNamesMap.entries.shuffled().take(count).map { it.toPair() }
    }
}