package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object CafeSingleton {

    val hashMapCafeWords = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapCafeWords["fruit juice"] = Triple("vaisių sultys", R.drawable.fruitjuice, R.raw.computer)
        hashMapCafeWords["apple juice"] = Triple("obuolių sultys", R.drawable.applejuice, R.raw.computer)
        hashMapCafeWords["soft drink"] = Triple("gaivus gerimas", R.drawable.softdrink, R.raw.computer)
        hashMapCafeWords["a pot"] = Triple("puodas", R.drawable.pot, R.raw.computer)
        hashMapCafeWords["hot or cold"] = Triple("karštas arbo šaltas", R.drawable.hotorcold, R.raw.computer)
        hashMapCafeWords["small, medium or large"] = Triple("mažas, vidutinis arbo didelis", R.drawable.smallmediumlarge, R.raw.computer)
        hashMapCafeWords["ice"] = Triple("ledas", R.drawable.ice, R.raw.computer)
        hashMapCafeWords["beer"] = Triple("alaus", R.drawable.beer, R.raw.computer)
        hashMapCafeWords["What would you like to drink?"] = Triple("Ko norėtumėte išgerti?", R.drawable.whatdrink, R.raw.computer)
        hashMapCafeWords["What size?"] = Triple("Kokio dydžio?", R.drawable.whatsize, R.raw.computer)

        hashMapCafeWords["how much does it cost?"] = Triple("kiek tai kainuoja?", R.drawable.howmuchcost, R.raw.computer)
        hashMapCafeWords["green tea"] = Triple("žalioji arbata", R.drawable.greentea, R.raw.computer)
        hashMapCafeWords["a bottle"] = Triple("butelis", R.drawable.bottle1, R.raw.computer)
        hashMapCafeWords["please come in"] = Triple("prašAu užEiti", R.drawable.comein, R.raw.computer)
        hashMapCafeWords["please sit here"] = Triple("prašAu sedĖti čia", R.drawable.sitdown, R.raw.computer)
        hashMapCafeWords["to order"] = Triple("užsakYti", R.drawable.toorder, R.raw.computer)
        hashMapCafeWords["please wait!"] = Triple("prašau palaukti!", R.drawable.towait1, R.raw.computer)
        hashMapCafeWords["Welcome"] = Triple("sveiki atvYkę!", R.drawable.welcome, R.raw.computer)
        hashMapCafeWords["I am very hungry"] = Triple("aš esu labai alkAna", R.drawable.hungry, R.raw.computer)
        hashMapCafeWords["She is a vegetarian"] = Triple("Ji yra vegetarė", R.drawable.vegetarian, R.raw.computer)

        hashMapCafeWords["dessert"] = Triple("desertas", R.drawable.dessert, R.raw.computer)
        hashMapCafeWords["beverage"] = Triple("gėrimas", R.drawable.beverage, R.raw.computer)
        hashMapCafeWords["ice cream"] = Triple("ledai", R.drawable.icecream, R.raw.computer)
        hashMapCafeWords["one piece"] = Triple("vieno gabalėlio", R.drawable.onepiece, R.raw.computer)
        hashMapCafeWords["a bill"] = Triple("sąskaita", R.drawable.bill, R.raw.computer)
        hashMapCafeWords["a receipt"] = Triple("kvitas", R.drawable.receipt, R.raw.computer)
        hashMapCafeWords["knife"] = Triple("peilis", R.drawable.knife, R.raw.computer)
        hashMapCafeWords["hard biscuit"] = Triple("kietas sausainis", R.drawable.hardbiscuit, R.raw.computer)
        hashMapCafeWords["taste and smell"] = Triple("skonis ir kvapas", R.drawable.taste, R.raw.computer)
    }

}