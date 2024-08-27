package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object FeelingsSingleton {

    val hashMapFeelings = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapFeelings["to feel"] = Triple("jausti", R.drawable.tofeel, R.raw.computer)
        hashMapFeelings["I feel happy"] = Triple("Aš jaučiousi laiminga", R.drawable.feelhappy, R.raw.computer)
        hashMapFeelings["happy"] = Triple("laimingas", R.drawable.happy, R.raw.computer)
        hashMapFeelings["sad"] = Triple("liudnas", R.drawable.sad, R.raw.computer)
        hashMapFeelings["She feels sad"] = Triple("Ji jaučiasi liūdna", R.drawable.shefeelssad, R.raw.computer)
        hashMapFeelings["to be sorry"] = Triple("gailėtis", R.drawable.sorry, R.raw.computer)
        hashMapFeelings["to be glad"] = Triple("džiaugtis", R.drawable.tobeglad, R.raw.computer)
        hashMapFeelings["How are you"] = Triple("kaip laikasi?", R.drawable.howru, R.raw.computer)
        hashMapFeelings["very nice"] = Triple("labai malonu", R.drawable.verynice, R.raw.computer)
        hashMapFeelings["cute"] = Triple("mielas", R.drawable.cute, R.raw.computer)

        hashMapFeelings["to like"] = Triple("mėgti", R.drawable.tolike, R.raw.tolike)
        hashMapFeelings["to love"] = Triple("mylėti", R.drawable.tolove, R.raw.love)
        hashMapFeelings["happiness"] = Triple("laimė", R.drawable.happiness, R.raw.computer)
        hashMapFeelings["sadness"] = Triple("liūdesys", R.drawable.sadness, R.raw.computer)
        hashMapFeelings["peace"] = Triple("ramybė", R.drawable.peace, R.raw.computer)

        hashMapFeelings["sick"] = Triple("serga", R.drawable.sick, R.raw.computer)
        hashMapFeelings["tired"] = Triple("pavargęs", R.drawable.tired, R.raw.computer)
    }

}