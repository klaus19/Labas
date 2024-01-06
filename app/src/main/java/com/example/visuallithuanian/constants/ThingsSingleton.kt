package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ThingsSingleton {

    val hashMapThingsWords = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapThingsWords["clothes"] = Triple("drabužiai", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["shirt"] = Triple("marškiniai", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["to put on"] = Triple("apsivilkti", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["to take off (clothes)"] = Triple("nusivilkti", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["short sleeves"] = Triple("trumpos rankovės", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["trousers"] = Triple("kelnės", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["skirt"] = Triple("sijonas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["a dress"] = Triple("suknelė", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["a white coat"] = Triple("baltas paltas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["jeans"] = Triple("džinsai", R.drawable.immediately, R.raw.computer)

        hashMapThingsWords["a ring"] = Triple("žiedas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["sun glasses"] = Triple("saulės akiniai", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["scarf"] = Triple("skara", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["a handbag"] = Triple("rankinukas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["Gold"] = Triple("auksas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["weight"] = Triple("svoris", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["capacity"] = Triple("talpa", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["dimensions (dimension)"] = Triple("matmenys (matmuo)", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["shoes"] = Triple("batai", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["sandals"] = Triple("sandalai", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["oil and gas"] = Triple("nafta ir dujos", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["guitar"] = Triple("gitara", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["piano"] = Triple("pianinas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["violin"] = Triple("smuikas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["letter"] = Triple("laiškas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["Envelope"] = Triple("vokas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["Postcard"] = Triple("atvirukas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["Package"] = Triple("paketas", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["picture/image"] = Triple("nuotrauka", R.drawable.immediately, R.raw.computer)
        hashMapThingsWords["drugs"] = Triple("vaistai", R.drawable.immediately, R.raw.computer)

        hashMapThingsWords["tavo dovana"] = Triple("tavo dovana", R.drawable.immediately, R.raw.computer)


    }
}