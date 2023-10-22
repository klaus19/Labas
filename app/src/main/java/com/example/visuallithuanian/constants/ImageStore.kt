package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ImageStore {

    val imagesNamesMap = hashMapOf(
        R.drawable.africa to Pair("Africa","Afrika"),
        R.drawable.asia to Pair("Asia","Azija"),
        R.drawable.europe to Pair("Europe","Europa"),
        R.drawable.antartica to Pair("Antarctica","Antarktida"),
        R.drawable.airplane to Pair("Aeroplane","Lėktuvas"),
        R.drawable.apple1 to Pair("Apple","Obuolys"),
        R.drawable.key to Pair("Key","raktas"),
        R.drawable.sleep to Pair("to sleep","miegoti")
    ).toList().shuffled().take(4).toMap()


}
