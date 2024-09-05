package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object FeelingsSingleton {

    val hashMapFeelings = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapFeelings["to feel"] = Triple("jausti", R.drawable.tofeel, R.raw.tofeel)
        hashMapFeelings["I feel happy"] = Triple("Aš jaučiousi laiminga", R.drawable.feelhappy, R.raw.ifeelhappy)
        hashMapFeelings["happy"] = Triple("laimingas", R.drawable.happy, R.raw.happy)
        hashMapFeelings["sad"] = Triple("liudnas", R.drawable.sad, R.raw.sad)
        hashMapFeelings["She feels sad"] = Triple("Ji jaučiasi liūdna", R.drawable.shefeelssad, R.raw.shefeelssad)
        hashMapFeelings["to be sorry"] = Triple("gailėtis", R.drawable.sorry, R.raw.tobesorry)
        hashMapFeelings["to be glad"] = Triple("džiaugtis", R.drawable.tobeglad, R.raw.tobeglad)
        hashMapFeelings["How are you"] = Triple("kaip laikasi?", R.drawable.howru, R.raw.howareyou)
        hashMapFeelings["very nice"] = Triple("labai malonu", R.drawable.verynice, R.raw.verynice)
        hashMapFeelings["cute"] = Triple("mielas", R.drawable.cute, R.raw.cute)

        hashMapFeelings["to like"] = Triple("mėgti", R.drawable.tolike, R.raw.tolike)
        hashMapFeelings["to love"] = Triple("mylėti", R.drawable.tolove, R.raw.love)
        hashMapFeelings["happiness"] = Triple("laimė", R.drawable.happiness, R.raw.happiness)
        hashMapFeelings["sadness"] = Triple("liūdesys", R.drawable.sadness, R.raw.sadness)
        hashMapFeelings["peace"] = Triple("ramybė", R.drawable.peace, R.raw.peace)

        hashMapFeelings["sick"] = Triple("serga", R.drawable.sick, R.raw.sick)
        hashMapFeelings["tired"] = Triple("pavargęs", R.drawable.tired, R.raw.tired)
    }

}