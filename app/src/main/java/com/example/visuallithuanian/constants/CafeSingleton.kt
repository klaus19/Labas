package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object CafeSingleton {

    val hashMapCafeWords = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapCafeWords["fruit juice"] = Triple("vaisių sultys", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["apple juice"] = Triple("obuolių sultys", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["soft drink"] = Triple("gaivus gerimas", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["a pot"] = Triple("puodas", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["hot or cold"] = Triple("karštas arbo šaltas", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["small, medium or large"] = Triple("mažas, vidutinis arbo didelis", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["ice"] = Triple("ledas", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["beer"] = Triple("alaus", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["What would you like to drink?"] = Triple("Ko norėtumėte išgerti?", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["What size?"] = Triple("Kokio dydžio?", R.drawable.stage, R.raw.computer)

        hashMapCafeWords["how much does it cost?"] = Triple("kiek tai kainuoja?", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["green tea"] = Triple("žalioji arbata", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["a bottle"] = Triple("butelis", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["please come in"] = Triple("prašAu užEiti", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["please sit here"] = Triple("prašAu sedĖti čia", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["to order"] = Triple("užsakYti", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["please wait!"] = Triple("prašau palaukti!", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["Welcome"] = Triple("sveiki atvYkę!", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["I am very hungry"] = Triple("aš esu labai alkAna", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["She is a vegetarian"] = Triple("Ji yra vegetarė", R.drawable.stage, R.raw.computer)

        hashMapCafeWords["dessert"] = Triple("desertas", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["beverage"] = Triple("gėrimas", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["ice cream"] = Triple("ledai", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["one piece"] = Triple("vieno gabalėlio", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["a bill"] = Triple("sąskaita", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["a receipt"] = Triple("kvitas", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["knife"] = Triple("peilis", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["hard biscuit"] = Triple("kietas sausainis", R.drawable.stage, R.raw.computer)
        hashMapCafeWords["taste and smell"] = Triple("skonis ir kvapas", R.drawable.stage, R.raw.computer)
    }

}