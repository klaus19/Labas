package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object AnimalsSingleton {

    val hashMapAnimals = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapAnimals["flight"] = Triple("skrydis", R.drawable.flight, R.raw.flight)
        hashMapAnimals["fish"] = Triple("žuvis", R.drawable.fish, R.raw.fish)
        hashMapAnimals["rabbit"] = Triple("triušis", R.drawable.rabbit, R.raw.rabbit)
        hashMapAnimals["hen"] = Triple("višta", R.drawable.hen, R.raw.hen)
        hashMapAnimals["deer"] = Triple("elnias", R.drawable.deer, R.raw.deer)
        hashMapAnimals["goat"] = Triple("ožka", R.drawable.goat, R.raw.goat)
        hashMapAnimals["zebra"] = Triple("zebras", R.drawable.zebra, R.raw.zebra)
        hashMapAnimals["worm"] = Triple("kirminas", R.drawable.worm, R.raw.worm)
        hashMapAnimals["shark"] = Triple("Ryklys", R.drawable.shark, R.raw.shark)
        hashMapAnimals["spider"] = Triple("voras", R.drawable.spider, R.raw.spider)
        hashMapAnimals["seagull"] = Triple("žuvėdra", R.drawable.seagull, R.raw.seagull)
        hashMapAnimals["chimpanzee"] = Triple("šimpanzė", R.drawable.chimpanzee, R.raw.chimpanzee)
        hashMapAnimals["crocodile"] = Triple("krokodilas", R.drawable.crocodile, R.raw.crocodile)
        hashMapAnimals["Lion"] = Triple("Liūtas", R.drawable.lion, R.raw.lion)
        hashMapAnimals["dolphin"] = Triple("delfinas", R.drawable.dolphin, R.raw.dolphin)
        hashMapAnimals["snake"] = Triple("gyvatė", R.drawable.snake, R.raw.snake)
        hashMapAnimals["donkey"] = Triple("asilas", R.drawable.donkey, R.raw.donkey)
        hashMapAnimals["tiger"] = Triple("tigras", R.drawable.tiger, R.raw.tiger)
        hashMapAnimals["mosquito"] = Triple("uodas", R.drawable.mosquito, R.raw.mosquito)
        hashMapAnimals["pig"] = Triple("kiaulė", R.drawable.pig, R.raw.pig)
        hashMapAnimals["jellyfish"] = Triple("medūza", R.drawable.jellyfish, R.raw.jellyfish)
        hashMapAnimals["bull"] = Triple("bulius", R.drawable.bull, R.raw.bull)
        hashMapAnimals["gorilla"] = Triple("gorila", R.drawable.gorilla, R.raw.gorilla)
        hashMapAnimals["elephant"] = Triple("dramblys", R.drawable.elephant, R.raw.elephant)
        hashMapAnimals["bird"] = Triple("paukštis", R.drawable.birds, R.raw.bird)
        hashMapAnimals["sheep"] = Triple("avis", R.drawable.sheep, R.raw.sheep)
        hashMapAnimals["giraffe"] = Triple("žirafa", R.drawable.giraffe, R.raw.giraffe)
        hashMapAnimals["cow"] = Triple("karvė", R.drawable.cow, R.raw.cow)
        hashMapAnimals["parrot"] = Triple("papūga", R.drawable.parrot, R.raw.parrot)
        hashMapAnimals["bee"] = Triple("bitė", R.drawable.bee, R.raw.bee)
        hashMapAnimals["monkey"] = Triple("beždžionė", R.drawable.monkey, R.raw.monkey)
        hashMapAnimals["chicken"] = Triple("vištiena", R.drawable.chicken, R.raw.chicken)
        hashMapAnimals["whale"] = Triple("banginis", R.drawable.whale, R.raw.whale)
        hashMapAnimals["butterfly"] = Triple("drugelis", R.drawable.butterfly, R.raw.butterfly)
        hashMapAnimals["kangaroo"] = Triple("kengūra", R.drawable.kangaroo, R.raw.kangaroo)
        hashMapAnimals["bear"] = Triple("meška", R.drawable.bear, R.raw.beer1)
        hashMapAnimals["horse"] = Triple("arklys", R.drawable.horse, R.raw.horse)
        hashMapAnimals["camel"] = Triple("kupranugaris", R.drawable.camel, R.raw.camel)
        hashMapAnimals["beetle"] = Triple("vabalas", R.drawable.beetle, R.raw.bettle)
    }
}