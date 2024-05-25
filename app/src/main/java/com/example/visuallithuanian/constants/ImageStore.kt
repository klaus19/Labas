package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ImageStore {
    val imagesNamesMap = mapOf(
        R.drawable.africa to Pair("Africa", "Afrika"),
        R.drawable.asia to Pair("Asia", "Azija"),
        R.drawable.europe to Pair("Europe", "Europa"),
        R.drawable.antartica to Pair("Antarctica", "Antarktida"),
        R.drawable.airplane to Pair("Aeroplane", "Lėktuvas"),
        R.drawable.apple1 to Pair("Apple", "Obuolys"),
        R.drawable.key to Pair("Key", "raktas"),
        R.drawable.sleep to Pair("to sleep", "miegoti"),
        R.drawable.shopping to Pair("to shop", "apsipirkti"),
        R.drawable.cinemascreen to Pair("to watch a movie", "Žiūrėti filmą"),
        // Add more entries as needed
    )

    fun getRandomPairs(count: Int): List<Pair<Int, Pair<String, String>>> {
        return imagesNamesMap.entries.shuffled().take(count).map { it.toPair() }
    }
}
