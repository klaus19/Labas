package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object MathsSingleton {

    val hashMapMaths = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapMaths["circle"] = Triple("apskritimas", R.drawable.circle,R.raw.circle)
        hashMapMaths["rectangle"] = Triple("stačiakampis", R.drawable.rectangle,R.raw.rectangle)
        hashMapMaths["a ball "] = Triple("rutulys", R.drawable.ball,R.raw.abowl)
        hashMapMaths["a cube"] = Triple("kubas", R.drawable.cube,R.raw.acube)
        hashMapMaths["high and low"] = Triple("aukštas ir žemas", R.drawable.highandlow,R.raw.highandlow)
        hashMapMaths["smooth"] = Triple("lygus", R.drawable.smooth,R.raw.smooth)
        hashMapMaths["soft cake"] = Triple("minkštas tortas", R.drawable.softcake,R.raw.softcake)
        hashMapMaths["area"] = Triple("plotas", R.drawable.area,R.raw.area)
        hashMapMaths["a class"] = Triple("pamoka", R.drawable.aclass,R.raw.aclass)
        hashMapMaths["are you ready?"] = Triple("Ar tu pasiruošusi?", R.drawable.ruready,R.raw.areyouready)
        hashMapMaths["homework"] = Triple("namų darbai", R.drawable.homework,R.raw.homework)
        hashMapMaths["a progress"] = Triple("pažanga", R.drawable.progress,R.raw.aprogress)
        hashMapMaths["more than that ..."] = Triple("daugiau negu, kad ...", R.drawable.morethanthat,R.raw.morethanthat)
    }
}