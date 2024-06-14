package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ImageStore {
    val imagesNamesMap = mutableMapOf<Int, Triple<String, String,Int>>()

    fun addImageResource(resId:Int,english:String,lithuanian:String,voiceClip:Int){
        imagesNamesMap[resId] = Triple(english,lithuanian,voiceClip)
    }

    fun removeImageResource(resId: Int){
        imagesNamesMap.remove(resId)
    }
    fun getRandomPairs(count: Int): List<Pair<Int, Triple<String, String,Int>>> {
        return imagesNamesMap.entries.shuffled().take(count).map { it.toPair() }
    }
}