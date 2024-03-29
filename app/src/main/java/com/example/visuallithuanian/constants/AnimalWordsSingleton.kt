package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object AnimalWordsSingleton {

    val hashMapAnimalWords = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapAnimalWords["wildlife"] = Triple("laukinės gamtos", R.drawable.wildlife, R.raw.computer)
        hashMapAnimalWords["cruel"] = Triple("žiaurus", R.drawable.cruel, R.raw.computer)
        hashMapAnimalWords["ladybird"] = Triple("boružėlė", R.drawable.ladybird, R.raw.computer)
        hashMapAnimalWords["scary"] = Triple("baisu", R.drawable.scary, R.raw.computer)
        hashMapAnimalWords["Catch"] = Triple("Sugauti", R.drawable.catch1, R.raw.computer)
        hashMapAnimalWords["captivity"] = Triple("nelaisvė", R.drawable.captivity, R.raw.computer)
        hashMapAnimalWords["swarm"] = Triple("spiečius", R.drawable.swarm, R.raw.computer)
        hashMapAnimalWords["Bitterness"] = Triple("Kartumas", R.drawable.bitterness, R.raw.computer)

        hashMapAnimalWords["grasshopper"] = Triple("žiogas", R.drawable.grasshopper, R.raw.computer)
        hashMapAnimalWords["disappear"] = Triple("išnykti", R.drawable.disappear, R.raw.computer)
        hashMapAnimalWords["carnivore"] = Triple("mėsėdis", R.drawable.carnivore, R.raw.computer)
        hashMapAnimalWords["creature"] = Triple("padaras", R.drawable.creature, R.raw.computer)
        hashMapAnimalWords["flock"] = Triple("kaimenę", R.drawable.flock, R.raw.computer)
        hashMapAnimalWords["worm"] = Triple("kirminas", R.drawable.worm, R.raw.computer)
        hashMapAnimalWords["Species"] = Triple("rūšis", R.drawable.species, R.raw.computer)
        hashMapAnimalWords["Extinction"] = Triple("išnykimas", R.drawable.extinction, R.raw.computer)
        hashMapAnimalWords["desert"] = Triple("dykuma", R.drawable.desert, R.raw.computer)

        hashMapAnimalWords["tusks"] = Triple("iltis", R.drawable.tusks, R.raw.computer)
        hashMapAnimalWords["pig"] = Triple("kiaulė", R.drawable.pig, R.raw.computer)
        hashMapAnimalWords["survival"] = Triple("išlikimas", R.drawable.survival, R.raw.computer)
        hashMapAnimalWords["summer"] = Triple("letena", R.drawable.summer, R.raw.computer)
        hashMapAnimalWords["fins"] = Triple("pelekai", R.drawable.fin, R.raw.computer)
        hashMapAnimalWords["cleaner"] = Triple("valytojas", R.drawable.cleaner, R.raw.computer)
    }

}