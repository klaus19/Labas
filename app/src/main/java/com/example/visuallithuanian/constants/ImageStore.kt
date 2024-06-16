package com.example.visuallithuanian.constants

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object ImageStore {
    private const val PREFERENCES_KEY = "ImageStore"
    private const val IMAGES_KEY = "images"
    val imagesNamesMap = mutableMapOf<Int, Triple<String, String, Int>>()

    fun addImageResource(resId: Int, english: String, lithuanian: String, voiceClip: Int) {
        imagesNamesMap[resId] = Triple(english, lithuanian, voiceClip)
    }

    fun removeImageResource(resId: Int) {
        imagesNamesMap.remove(resId)
    }

    fun getRandomPairs(count: Int): List<Pair<Int, Triple<String, String, Int>>> {
        return imagesNamesMap.entries.shuffled().take(count).map { it.toPair() }
    }

    fun saveToPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val jsonArray = JSONArray()

        for ((resId, triple) in imagesNamesMap) {
            val jsonObject = JSONObject().apply {
                put("resId", resId)
                put("english", triple.first)
                put("lithuanian", triple.second)
                put("voiceClip", triple.third)
            }
            jsonArray.put(jsonObject)
        }

        editor.putString(IMAGES_KEY, jsonArray.toString())
        editor.apply()
    }

    fun loadFromPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString(IMAGES_KEY, null)

        if (jsonString != null) {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val resId = jsonObject.getInt("resId")
                val english = jsonObject.getString("english")
                val lithuanian = jsonObject.getString("lithuanian")
                val voiceClip = jsonObject.getInt("voiceClip")

                imagesNamesMap[resId] = Triple(english, lithuanian, voiceClip)
            }
        }
    }
}
