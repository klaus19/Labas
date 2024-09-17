package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object CafeSingleton {

    val hashMapCafeWords = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapCafeWords["fruit juice"] = Triple("gaivusis gėrimas", R.drawable.fruitjuice, R.raw.fruitjuice)
        hashMapCafeWords["apple juice"] = Triple("obuolių sultys", R.drawable.applejuice, R.raw.applejuice)
        hashMapCafeWords["soft drink"] = Triple("gaivus gerimas", R.drawable.softdrink, R.raw.softdrink)
        hashMapCafeWords["a pot"] = Triple("puodas", R.drawable.pot, R.raw.apot)
        hashMapCafeWords["hot or cold"] = Triple("karštas arbo šaltas", R.drawable.hotorcold, R.raw.hotorcold)
        hashMapCafeWords["small, medium or large"] = Triple("mažas, vidutinis arbo didelis", R.drawable.smallmediumlarge, R.raw.smallmediumlarge)
        hashMapCafeWords["ice"] = Triple("ledas", R.drawable.ice, R.raw.ice)
        hashMapCafeWords["beer"] = Triple("alaus", R.drawable.beer, R.raw.beer)
        hashMapCafeWords["What would you like to drink?"] = Triple("Ko norėtumėte išgerti?", R.drawable.whatdrink, R.raw.whatwouldliketodrink)
        hashMapCafeWords["What size?"] = Triple("Kokio dydžio?", R.drawable.whatsize, R.raw.whatsize)

        hashMapCafeWords["how much does it cost?"] = Triple("kiek tai kainuoja?", R.drawable.howmuchcost, R.raw.howmuchdoesitcoast)
        hashMapCafeWords["green tea"] = Triple("žalioji arbata", R.drawable.greentea, R.raw.greentea)
        hashMapCafeWords["a bottle"] = Triple("butelis", R.drawable.bottle1, R.raw.abottle)
        hashMapCafeWords["please come in"] = Triple("prašAu užEiti", R.drawable.comein, R.raw.pleasecomein)
        hashMapCafeWords["please sit here"] = Triple("prašau sėstis čia", R.drawable.sitdown, R.raw.pleasesithere)
        hashMapCafeWords["to order"] = Triple("užsakYti", R.drawable.toorder, R.raw.toorder)
        hashMapCafeWords["please wait!"] = Triple("prašau palaukti!", R.drawable.towait1, R.raw.pleasewait)
        hashMapCafeWords["Welcome"] = Triple("sveiki atvYkę!", R.drawable.welcome, R.raw.welcome)
        hashMapCafeWords["I am very hungry"] = Triple("aš esu labai alkAna", R.drawable.hungry, R.raw.iamveryhungry)
        hashMapCafeWords["She is a vegetarian"] = Triple("Ji yra vegetarė", R.drawable.vegetarian, R.raw.sheisvegetarian)

        hashMapCafeWords["dessert"] = Triple("desertas", R.drawable.dessert, R.raw.dessert)
        hashMapCafeWords["beverage"] = Triple("gėrimas", R.drawable.beverage, R.raw.beverage)
        hashMapCafeWords["ice cream"] = Triple("ledai", R.drawable.icecream, R.raw.icecream)
        hashMapCafeWords["one piece"] = Triple("vieno gabalėlio", R.drawable.onepiece, R.raw.onepiece)
        hashMapCafeWords["a bill"] = Triple("sąskaita", R.drawable.bill, R.raw.abill)
        hashMapCafeWords["a receipt"] = Triple("kvitas", R.drawable.receipt, R.raw.receipt)
        hashMapCafeWords["knife"] = Triple("peilis", R.drawable.knife, R.raw.knife)
        hashMapCafeWords["hard biscuit"] = Triple("kietas sausainis", R.drawable.hardbiscuit, R.raw.hardbiscuit)
        hashMapCafeWords["taste and smell"] = Triple("skonis ir kvapas", R.drawable.taste, R.raw.tasteandsmell)
    }

}