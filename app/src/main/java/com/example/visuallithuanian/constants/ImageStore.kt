package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ImageStore {

    val imagesNamesMap = hashMapOf(
        R.drawable.africa to Pair("Africa","Afrika"),
        R.drawable.asia to Pair("Asia","Azija"),
        R.drawable.europe to Pair("Europe","Europa"),
        R.drawable.antartica to Pair("Antarctica","Antarktida"),
        R.drawable.airplane to Pair("Aeroplane","Lėktuvas"),
        R.drawable.apple1 to Pair("Apple","Obuolys"),
        R.drawable.key to Pair("Key","raktas"),
        R.drawable.sleep to Pair("to sleep","miegoti"),
        R.drawable.shopping to Pair("to shop","apsipirkti"),
        R.drawable.cinemascreen to Pair("to watch a movie","Žiūrėti filmą"),
        R.drawable.goshopping to Pair("I go shopping","aš einu apsipirkti"),
        R.drawable.what1 to Pair("What is it?","Kas tai?"),
        R.drawable.japanesefood to Pair("Japanese food","Japonų maistas"),
        R.drawable.hotcoffee to Pair("hot coffee","karšta kava"),
        R.drawable.eat to Pair("to eat","valgyti"),
        R.drawable.ofcourse to Pair("Of course","Žinoma"),
        R.drawable.rice to Pair("rice","ryžiai"),
        R.drawable.soup1 to Pair("soup","sriuba"),
        R.drawable.bread1 to Pair("Bread","Duona"),
        R.drawable.water1 to Pair("water","vanduo"),
        R.drawable.glass1 to Pair("a glass","stiklinė"),
        R.drawable.apple1 to Pair("apple","obuolys"),
        R.drawable.cost to Pair("to cost","kainuoti"),
        R.drawable.parents1 to Pair("parents","tėvai"),
        R.drawable.classmate to Pair("classmate","klasiokas"),
        R.drawable.friends1 to Pair("my friends","Mano draugai"),
        R.drawable.little to Pair("a little","šiek tiek"),
        R.drawable.go to Pair("Let's go","eikime!"),
        R.drawable.more to Pair("more","daugiau"),
        R.drawable.hand to Pair("hand","ranka"),
        R.drawable.stopsign to Pair("to stop","nustoti"),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),

    ).toList().shuffled().take(4).toMap()
}
