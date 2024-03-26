package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object WeeklyBasicSingleton {
    
    val hashMapWeeklyBasics = HashMap<String,Triple<String, Int, Int>>()
    
    init {
        hashMapWeeklyBasics["elder sister"] = Triple("vyresnė sesuo", R.drawable.eldersister, R.raw.computer)
        hashMapWeeklyBasics["older brother"] = Triple("vyresnis brolis", R.drawable.olderbrother, R.raw.computer)
        hashMapWeeklyBasics["Brothers and sisters "] = Triple("Broliai ir seserys", R.drawable.brothersandsisters, R.raw.computer)
        hashMapWeeklyBasics["she is his younger sister"] = Triple("Ji yra jo jaunesnė sesuo", R.drawable.heryoungersister, R.raw.computer)
        hashMapWeeklyBasics["how old is she?"] = Triple("Kiek jam metu?", R.drawable.oldisshe, R.raw.computer)
        hashMapWeeklyBasics["This girl"] = Triple("Ši mergaitė", R.drawable.thisgirl, R.raw.computer)
        hashMapWeeklyBasics["to weigh"] = Triple("sverti", R.drawable.toweigh, R.raw.computer)
        hashMapWeeklyBasics["one hundred"] = Triple("šimtas", R.drawable.onehundred, R.raw.computer)
        hashMapWeeklyBasics["This boy"] = Triple("Šis berniukas", R.drawable.thisboy, R.raw.computer)
        hashMapWeeklyBasics["two pencils"] = Triple("du pieštukai", R.drawable.twopencils, R.raw.computer)
        hashMapWeeklyBasics["a pen"] = Triple("rašiklis", R.drawable.apen, R.raw.computer)
        hashMapWeeklyBasics["to meet"] = Triple("susitikti", R.drawable.meeting1, R.raw.computer)
        hashMapWeeklyBasics["paper"] = Triple("popierius", R.drawable.paper, R.raw.computer)
    }
}