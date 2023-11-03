package com.example.visuallithuanian.constants

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.visuallithuanian.R

object ImageStore {


    val imagesNamesMap = hashMapOf(
        R.drawable.africa to "Afrika",
        R.drawable.asia to "Azija",
        R.drawable.europe to "Europa",
        R.drawable.antartica to "Antarktida",
        R.drawable.airplane to "Lėktuvas",
        R.drawable.apple1 to "Obuolys",
        R.drawable.key to "raktas",
        R.drawable.sleep to "miegoti",
        R.drawable.weekend to "Weekend",
        R.drawable.tomorrow to "rytoj",
        R.drawable.we to "We",
        R.drawable.inafternoon to "In Afternoon",
        R.drawable.halfaday to "Halfaday",

    ).toList().shuffled().take(4).toMap()

}
