package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ImageStore {

    val imagesNamesMap = hashMapOf(
        R.drawable.africa to "Africa",
        R.drawable.asia to "Asia",
        R.drawable.europe to "Europe",
        R.drawable.antartica to "Antarctica",
        R.drawable.airplane to "Airplane"
    ).toList().shuffled().take(4).toMap()


}
