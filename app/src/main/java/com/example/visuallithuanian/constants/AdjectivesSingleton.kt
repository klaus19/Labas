package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object AdjectivesSingleton {

    val hashMapAdjectives = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapAdjectives["Good"] = Triple("Geras", R.drawable.good, R.raw.computer)
        hashMapAdjectives["bad"] = Triple("blogas", R.drawable.bad, R.raw.computer)
        hashMapAdjectives["beautiful"] = Triple("gražus", R.drawable.beautiful, R.raw.computer)
        hashMapAdjectives["old"] = Triple("senas", R.drawable.old, R.raw.computer)
        hashMapAdjectives["big"] = Triple("didelis", R.drawable.big, R.raw.computer)
        hashMapAdjectives["cozy"] = Triple("jaukus", R.drawable.cozy, R.raw.computer)
        hashMapAdjectives["long"] = Triple("ilgas", R.drawable.long1, R.raw.computer)
        hashMapAdjectives["new"] = Triple("naujas", R.drawable.new1, R.raw.computer)
        hashMapAdjectives["short"] = Triple("trumpas", R.drawable.shorts, R.raw.computer)
        hashMapAdjectives["interesting"] = Triple("įdomus", R.drawable.interesting, R.raw.computer)

        hashMapAdjectives["jolly"] = Triple("linksmas", R.drawable.jolly, R.raw.computer)
        hashMapAdjectives["friendly"] = Triple("draugiškas", R.drawable.friendly11, R.raw.computer)
        hashMapAdjectives["cheap"] = Triple("pigu", R.drawable.cheap, R.raw.computer)
        hashMapAdjectives["Expensive"] = Triple("brangus", R.drawable.expensive, R.raw.computer)
        hashMapAdjectives["cold"] = Triple("šalta", R.drawable.cold, R.raw.computer)
        hashMapAdjectives["hot"] = Triple("karšta", R.drawable.hot, R.raw.computer)
        hashMapAdjectives["fresh"] = Triple("šviežias", R.drawable.fresh, R.raw.computer)
        hashMapAdjectives["safe"] = Triple("saugus", R.drawable.safe, R.raw.computer)
        hashMapAdjectives["dangerous"] = Triple("pavojinga", R.drawable.dangerous, R.raw.computer)
        hashMapAdjectives["soft"] = Triple("minkštas", R.drawable.soft, R.raw.computer)

        hashMapAdjectives["heavy"] = Triple("sunkus", R.drawable.heavy, R.raw.computer)
        hashMapAdjectives["young"] = Triple("jaunas", R.drawable.young, R.raw.computer)
        hashMapAdjectives["normal"] = Triple("normalus", R.drawable.normal, R.raw.computer)
        hashMapAdjectives["different"] = Triple("skirtingi", R.drawable.different, R.raw.computer)
        hashMapAdjectives["important"] = Triple("svarbu", R.drawable.important, R.raw.computer)
        hashMapAdjectives["comfortable"] = Triple("patogus", R.drawable.comfortable, R.raw.computer)
        hashMapAdjectives["dry"] = Triple("sausas", R.drawable.dry, R.raw.computer)
        hashMapAdjectives["wet"] = Triple("šlapias", R.drawable.wet, R.raw.computer)
        hashMapAdjectives["extra"] = Triple("papildomas", R.drawable.extra, R.raw.computer)
        hashMapAdjectives["shared room"] = Triple("bendras kambarys", R.drawable.sharedroom, R.raw.computer)

        hashMapAdjectives["enough"] = Triple("pakankamai", R.drawable.enough, R.raw.computer)
        hashMapAdjectives["successful"] = Triple("sėkmingas", R.drawable.successful, R.raw.computer)
        hashMapAdjectives["very strong"] = Triple("labai stiprus", R.drawable.strong, R.raw.computer)
        hashMapAdjectives["funny"] = Triple("juokingas", R.drawable.funny, R.raw.computer)


    }



}