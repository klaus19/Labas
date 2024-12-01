package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object AnimalWordsSingleton {

    val hashMapAnimalWords = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapAnimalWords["wildlife"] = Triple("laukinė gamta", R.drawable.wildlife, R.raw.wildlife)
        hashMapAnimalWords["cruel"] = Triple("žiaurus", R.drawable.cruel, R.raw.cruel)
        hashMapAnimalWords["ladybird"] = Triple("boružėlė", R.drawable.ladybird, R.raw.ladybird)
        hashMapAnimalWords["Catch"] = Triple("Sugauti", R.drawable.catch1, R.raw.catch1)
        hashMapAnimalWords["captivity"] = Triple("nelaisvė", R.drawable.captivity, R.raw.captivity)
        hashMapAnimalWords["swarm"] = Triple("spiečius", R.drawable.swarm, R.raw.swarm)
        hashMapAnimalWords["Bitterness"] = Triple("Kartumas", R.drawable.bitterness, R.raw.bitternss)
        hashMapAnimalWords["grasshopper"] = Triple("žiogas", R.drawable.grasshopper, R.raw.grasshopper)
        hashMapAnimalWords["disappear"] = Triple("išnykti", R.drawable.disappear, R.raw.disappear)
        hashMapAnimalWords["carnivore"] = Triple("mėsėdis", R.drawable.carnivore, R.raw.carnivor)
        hashMapAnimalWords["creature"] = Triple("padaras", R.drawable.creature, R.raw.creature)
        hashMapAnimalWords["flock"] = Triple("kaimenė", R.drawable.flock, R.raw.flock)
        hashMapAnimalWords["Species"] = Triple("rūšis", R.drawable.species, R.raw.species)
        hashMapAnimalWords["Extinction"] = Triple("išnykimas", R.drawable.extinction, R.raw.extinction)
        hashMapAnimalWords["desert"] = Triple("dykuma", R.drawable.desert, R.raw.desert)
        hashMapAnimalWords["tusks"] = Triple("iltys", R.drawable.tusks, R.raw.tusks)
        hashMapAnimalWords["survival"] = Triple("išlikimas", R.drawable.survival, R.raw.survival)
        hashMapAnimalWords["summer"] = Triple("vasara", R.drawable.summer, R.raw.summer)
        hashMapAnimalWords["fins"] = Triple("pelekai", R.drawable.fin, R.raw.fins)
        hashMapAnimalWords["cleaner"] = Triple("valytojas", R.drawable.cleaner, R.raw.cleaner)

        hashMapAnimalWords["worm"] = Triple("kirminas", R.drawable.worm, R.raw.worm)
        hashMapAnimalWords["pig"] = Triple("kiaulė", R.drawable.pig, R.raw.pig)
        hashMapAnimalWords["scary"] = Triple("baisu", R.drawable.scary, R.raw.scary)
    }

}