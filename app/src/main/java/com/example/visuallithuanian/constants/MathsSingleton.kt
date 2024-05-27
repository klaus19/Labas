package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object MathsSingleton {

    val hashMapMaths = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapMaths["circle"] = Triple("apskritimas", R.drawable.circle,R.raw.computer)
        hashMapMaths["rectangle"] = Triple("stačiakampis", R.drawable.rectangle,R.raw.computer)
        hashMapMaths["a ball "] = Triple("rutulys", R.drawable.ball,R.raw.computer)
        hashMapMaths["a cube"] = Triple("kubas", R.drawable.cube,R.raw.computer)
        hashMapMaths["high and low"] = Triple("aukštas ir žemas", R.drawable.highandlow,R.raw.computer)
        hashMapMaths["smooth"] = Triple("lygus", R.drawable.smooth,R.raw.computer)
        hashMapMaths["soft cake"] = Triple("minkštas tortas", R.drawable.softcake,R.raw.computer)
        hashMapMaths["area"] = Triple("plotas", R.drawable.area,R.raw.computer)

        hashMapMaths["a class"] = Triple("pamoka", R.drawable.aclass,R.raw.computer)
        hashMapMaths["are you ready?"] = Triple("Ar tu pasiruošusi?", R.drawable.ruready,R.raw.computer)
        hashMapMaths["homework"] = Triple("namų darbas", R.drawable.homework,R.raw.computer)
        hashMapMaths["a progress"] = Triple("pažanga", R.drawable.progress,R.raw.computer)
        hashMapMaths["more than that ..."] = Triple("daugiau negu, kad ...", R.drawable.morethanthat,R.raw.computer)

    }
}