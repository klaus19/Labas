package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object WeeklyBasicSingleton {
    
    val hashMapWeeklyBasics = HashMap<String,Triple<String, Int, Int>>()
    
    init {
        hashMapWeeklyBasics["elder sister"] = Triple("vyresnė sesuo", R.drawable.eldersister, R.raw.eldersister)
        hashMapWeeklyBasics["older brother"] = Triple("vyresnis brolis", R.drawable.olderbrother, R.raw.olderbrother)
        hashMapWeeklyBasics["Brothers and sisters "] = Triple("Broliai ir seserys", R.drawable.brothersandsisters, R.raw.brothersandsister)
        hashMapWeeklyBasics["she is his younger sister"] = Triple("Ji yra jo jaunesnė sesuo", R.drawable.heryoungersister, R.raw.sheisyoungersister)
        hashMapWeeklyBasics["how old is she?"] = Triple("Kiek jai metų?", R.drawable.oldisshe, R.raw.howoldishe)
        hashMapWeeklyBasics["This girl"] = Triple("Ši mergaitė", R.drawable.thisgirl, R.raw.thisgirl)
        hashMapWeeklyBasics["to weigh"] = Triple("sverti", R.drawable.toweigh, R.raw.toweigh)
        hashMapWeeklyBasics["one hundred"] = Triple("šimtas", R.drawable.onehundred, R.raw.onehundreed)
        hashMapWeeklyBasics["This boy"] = Triple("Šis berniukas", R.drawable.thisboy, R.raw.thisboy)
        hashMapWeeklyBasics["two pencils"] = Triple("du pieštukai", R.drawable.twopencils, R.raw.twopencils)
        hashMapWeeklyBasics["a pen"] = Triple("rašiklis", R.drawable.apen, R.raw.apen)
        hashMapWeeklyBasics["to meet"] = Triple("susitikti", R.drawable.meeting1, R.raw.tomeet)
        hashMapWeeklyBasics["paper"] = Triple("popierius", R.drawable.paper, R.raw.paper)
    }
}