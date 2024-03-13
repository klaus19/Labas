package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object AnimalsSingleton {

    val hashMapAnimals = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapAnimals["flight"] = Triple("skrydis", R.drawable.flight, R.raw.computer)
        hashMapAnimals["fish"] = Triple("žuvis", R.drawable.fish, R.raw.computer)
        hashMapAnimals["rabbit"] = Triple("triušis", R.drawable.rabbit, R.raw.computer)
        hashMapAnimals["hen"] = Triple("višta", R.drawable.hen, R.raw.computer)
        hashMapAnimals["deer"] = Triple("elnias", R.drawable.deer, R.raw.computer)
        hashMapAnimals["goat"] = Triple("ožka", R.drawable.goat, R.raw.computer)
        hashMapAnimals["zebras"] = Triple("zebras", R.drawable.zebra, R.raw.computer)
        hashMapAnimals["worm"] = Triple("kirminas", R.drawable.worm, R.raw.computer)
        hashMapAnimals["shark"] = Triple("Ryklys", R.drawable.shark, R.raw.computer)
        hashMapAnimals["voras"] = Triple("spider", R.drawable.spider, R.raw.computer)

        hashMapAnimals["seagull"] = Triple("žuvėdra", R.drawable.seagull, R.raw.computer)
        hashMapAnimals["chimpanzee"] = Triple("šimpanzė", R.drawable.chimpanzee, R.raw.computer)
        hashMapAnimals["crocodile"] = Triple("krokodilas", R.drawable.crocodile, R.raw.computer)
        hashMapAnimals["Lions"] = Triple("Liūtas", R.drawable.lion, R.raw.computer)
        hashMapAnimals["dolphin"] = Triple("delfinas", R.drawable.dolphin, R.raw.computer)
        hashMapAnimals["snake"] = Triple("gyvatė", R.drawable.snake, R.raw.computer)
        hashMapAnimals["donkey"] = Triple("asilas", R.drawable.donkey, R.raw.computer)
        hashMapAnimals["tiger"] = Triple("tigras", R.drawable.tiger, R.raw.computer)
        hashMapAnimals["mosquito"] = Triple("uodas", R.drawable.mosquito, R.raw.computer)
        hashMapAnimals["pig"] = Triple("kiaulė", R.drawable.pig, R.raw.computer)

        hashMapAnimals["jellyfish"] = Triple("medūza", R.drawable.jellyfish, R.raw.computer)
        hashMapAnimals["bull"] = Triple("bulius", R.drawable.bull, R.raw.computer)
        hashMapAnimals["gorilla"] = Triple("gorila", R.drawable.gorilla, R.raw.computer)
        hashMapAnimals["elephant"] = Triple("dramblys", R.drawable.elephant, R.raw.computer)
        hashMapAnimals["bird"] = Triple("paukštis", R.drawable.birds, R.raw.computer)
        hashMapAnimals["sheep"] = Triple("avis", R.drawable.sheep, R.raw.computer)
        hashMapAnimals["giraffe"] = Triple("žirafa", R.drawable.giraffe, R.raw.computer)
        hashMapAnimals["cow"] = Triple("karvė", R.drawable.cow, R.raw.computer)
        hashMapAnimals["parrot"] = Triple("papūga", R.drawable.parrot, R.raw.computer)
        hashMapAnimals["bee"] = Triple("bitė", R.drawable.bee, R.raw.computer)

        hashMapAnimals["monkey"] = Triple("beždžionė", R.drawable.monkey, R.raw.computer)
        hashMapAnimals["chicken"] = Triple("vištiena", R.drawable.chicken, R.raw.computer)
        hashMapAnimals["whale"] = Triple("banginis", R.drawable.whale, R.raw.computer)
        hashMapAnimals["butterfly"] = Triple("drugelis", R.drawable.butterfly, R.raw.computer)
        hashMapAnimals["kangaroo"] = Triple("kengūra", R.drawable.kangaroo, R.raw.computer)
        hashMapAnimals["bear"] = Triple("meška", R.drawable.bear, R.raw.computer)
        hashMapAnimals["horse"] = Triple("arklys", R.drawable.horse, R.raw.computer)
        hashMapAnimals["camel"] = Triple("kupranugaris", R.drawable.camel, R.raw.computer)
        hashMapAnimals["beetle"] = Triple("vabalas", R.drawable.beetle, R.raw.computer)
    }
}