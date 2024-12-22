package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ThingsSingleton {

    val hashMapThingsWords = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapThingsWords["clothes"] = Triple("drabužiai", R.drawable.clothes, R.raw.clothes)
        hashMapThingsWords["shirt"] = Triple("marškiniai", R.drawable.shirt, R.raw.shirt)
        hashMapThingsWords["to put on"] = Triple("apsivilkti", R.drawable.toputon, R.raw.toputon)
        hashMapThingsWords["to take off (clothes)"] = Triple("nusivilkti", R.drawable.totake, R.raw.totakeoff)
        hashMapThingsWords["short sleeves"] = Triple("trumpos rankovės", R.drawable.shortsleeves, R.raw.shortsleeves)
        hashMapThingsWords["trousers"] = Triple("kelnės", R.drawable.trousers, R.raw.trousers)
        hashMapThingsWords["skirt"] = Triple("sijonas", R.drawable.skirt, R.raw.skirt)
        hashMapThingsWords["a dress"] = Triple("suknelė", R.drawable.dress, R.raw.address)
        hashMapThingsWords["a white coat"] = Triple("baltas paltas", R.drawable.whitecoat, R.raw.awhitecoat)
        hashMapThingsWords["jeans"] = Triple("džinsai", R.drawable.jeans, R.raw.jeans)

        hashMapThingsWords["a ring"] = Triple("žiedas", R.drawable.ring, R.raw.aring)
        hashMapThingsWords["sun glasses"] = Triple("saulės akiniai", R.drawable.sunglasses, R.raw.sunglasses)
        hashMapThingsWords["scarf"] = Triple("šalikas", R.drawable.scarf, R.raw.scarf)
        hashMapThingsWords["a handbag"] = Triple("rankinukas", R.drawable.handbag, R.raw.ahandbag)
        hashMapThingsWords["Gold"] = Triple("auksas", R.drawable.gold, R.raw.gold)
        hashMapThingsWords["weight"] = Triple("svoris", R.drawable.weight, R.raw.weight)
        hashMapThingsWords["capacity"] = Triple("talpa", R.drawable.capacity, R.raw.capacity)
        hashMapThingsWords["dimensions"] = Triple("matmenys", R.drawable.dimension, R.raw.dimensions)
        hashMapThingsWords["shoes"] = Triple("batai", R.drawable.shoes, R.raw.shoes)
        hashMapThingsWords["sandals"] = Triple("sandalai", R.drawable.sandals, R.raw.sandals)
        hashMapThingsWords["oil and gas"] = Triple("nafta ir dujos", R.drawable.oilandgas, R.raw.oilandgas)
        hashMapThingsWords["guitar"] = Triple("gitara", R.drawable.guitar, R.raw.guitar)
        hashMapThingsWords["piano"] = Triple("pianinas", R.drawable.piano, R.raw.piano)
        hashMapThingsWords["violin"] = Triple("smuikas", R.drawable.violin, R.raw.violin)
        hashMapThingsWords["letter"] = Triple("laiškas", R.drawable.letter, R.raw.letter)
        hashMapThingsWords["envelope"] = Triple("vokas", R.drawable.envelop1, R.raw.envelope)
        hashMapThingsWords["postcard"] = Triple("atvirukas", R.drawable.postcard, R.raw.postcard)
        hashMapThingsWords["package"] = Triple("paketas", R.drawable.package1, R.raw.package1)
        hashMapThingsWords["picture"] = Triple("nuotrauka", R.drawable.picture, R.raw.picture)
        hashMapThingsWords["drugs"] = Triple("vaistai", R.drawable.drugs, R.raw.drugs)

        hashMapThingsWords["your present"] = Triple("tavo dovana", R.drawable.yourgift, R.raw.yourpresent)


    }
}