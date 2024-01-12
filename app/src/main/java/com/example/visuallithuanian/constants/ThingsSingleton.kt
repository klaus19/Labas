package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ThingsSingleton {

    val hashMapThingsWords = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapThingsWords["clothes"] = Triple("drabužiai", R.drawable.clothes, R.raw.computer)
        hashMapThingsWords["shirt"] = Triple("marškiniai", R.drawable.shirt, R.raw.computer)
        hashMapThingsWords["to put on"] = Triple("apsivilkti", R.drawable.toputon, R.raw.computer)
        hashMapThingsWords["to take off (clothes)"] = Triple("nusivilkti", R.drawable.totake, R.raw.computer)
        hashMapThingsWords["short sleeves"] = Triple("trumpos rankovės", R.drawable.shortsleeves, R.raw.computer)
        hashMapThingsWords["trousers"] = Triple("kelnės", R.drawable.trousers, R.raw.computer)
        hashMapThingsWords["skirt"] = Triple("sijonas", R.drawable.skirt, R.raw.computer)
        hashMapThingsWords["a dress"] = Triple("suknelė", R.drawable.dress, R.raw.computer)
        hashMapThingsWords["a white coat"] = Triple("baltas paltas", R.drawable.whitecoat, R.raw.computer)
        hashMapThingsWords["jeans"] = Triple("džinsai", R.drawable.jeans, R.raw.computer)

        hashMapThingsWords["a ring"] = Triple("žiedas", R.drawable.ring, R.raw.computer)
        hashMapThingsWords["sun glasses"] = Triple("saulės akiniai", R.drawable.sunglasses, R.raw.computer)
        hashMapThingsWords["scarf"] = Triple("skara", R.drawable.scarf, R.raw.computer)
        hashMapThingsWords["a handbag"] = Triple("rankinukas", R.drawable.handbag, R.raw.computer)
        hashMapThingsWords["Gold"] = Triple("auksas", R.drawable.gold, R.raw.computer)
        hashMapThingsWords["weight"] = Triple("svoris", R.drawable.weight, R.raw.computer)
        hashMapThingsWords["capacity"] = Triple("talpa", R.drawable.capacity, R.raw.computer)
        hashMapThingsWords["dimensions (dimension)"] = Triple("matmenys (matmuo)", R.drawable.dimension, R.raw.computer)
        hashMapThingsWords["shoes"] = Triple("batai", R.drawable.shoes, R.raw.computer)
        hashMapThingsWords["sandals"] = Triple("sandalai", R.drawable.sandals, R.raw.computer)
        hashMapThingsWords["oil and gas"] = Triple("nafta ir dujos", R.drawable.oilandgas, R.raw.computer)
        hashMapThingsWords["guitar"] = Triple("gitara", R.drawable.guitar, R.raw.computer)
        hashMapThingsWords["piano"] = Triple("pianinas", R.drawable.piano, R.raw.computer)
        hashMapThingsWords["violin"] = Triple("smuikas", R.drawable.violin, R.raw.computer)
        hashMapThingsWords["letter"] = Triple("laiškas", R.drawable.letter, R.raw.computer)
        hashMapThingsWords["Envelope"] = Triple("vokas", R.drawable.envelop1, R.raw.computer)
        hashMapThingsWords["Postcard"] = Triple("atvirukas", R.drawable.postcard, R.raw.computer)
        hashMapThingsWords["Package"] = Triple("paketas", R.drawable.package1, R.raw.computer)
        hashMapThingsWords["picture/image"] = Triple("nuotrauka", R.drawable.picture, R.raw.computer)
        hashMapThingsWords["drugs"] = Triple("vaistai", R.drawable.drugs, R.raw.computer)

        hashMapThingsWords["your gift"] = Triple("tavo dovana", R.drawable.yourgift, R.raw.computer)


    }
}