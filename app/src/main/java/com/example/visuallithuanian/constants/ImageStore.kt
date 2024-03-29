package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R
import com.example.visuallithuanian.Utils.shuffleList

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
        R.drawable.march to Pair("March","Kovas"),
        R.drawable.february to Pair("February","Vasaris"),
        R.drawable.january to Pair("January","Sausis"),
        R.drawable.saturday to Pair("Saturday","Šeštadienis"),
        R.drawable.friday to Pair("Friday","penktadienis"),
        R.drawable.thursday to Pair("Thursday","Ketvirtadienis"),
        R.drawable.wednesday to Pair("Wednesday","Trečiadienis"),
        R.drawable.tuesday to Pair("Tuesday","Antradienis"),
        R.drawable.monday to Pair("Monday","Pirmadienis"),
        R.drawable.sunday to Pair("Sunday","Sekmadienis"),
        R.drawable.autumn to Pair("autumn","ruduo"),
        R.drawable.summer to Pair("Summer","vasara"),
        R.drawable.spring to Pair("Spring","pavasaris"),
        R.drawable.december to Pair("December","Gruodis"),
        R.drawable.november to Pair("November","lapkritis"),
        R.drawable.october to Pair("October","Spalis"),
        R.drawable.september to Pair("September","Rugsėjis"),
        R.drawable.august to Pair("August","Rugpjūtis"),
        R.drawable.july to Pair("July","Liepa"),
        R.drawable.june to Pair("June","Birželis"),
        R.drawable.may to Pair("May","Gegužė"),
        R.drawable.april to Pair("April","Balandis"),
        R.drawable.march to Pair("March","Kovas"),
        R.drawable.noon to Pair("Noon","vidurdienis"),
        R.drawable.morning to Pair("Morning","rytas"),
        R.drawable.evening to Pair("evening","vakaras"),
        R.drawable.beforesleep to Pair("before sleep","prieš miegą"),
        R.drawable.halfaday to Pair("half a day","pusę dienos"),
        R.drawable.midnight to Pair("Midnight","vidurnaktis"),
        R.drawable.decade to Pair("decade","dešimtmetį"),
        R.drawable.season to Pair("Season","sezonas"),
        R.drawable.year to Pair("Year","metai"),
        R.drawable.month to Pair("Month","Mėnuo"),
        R.drawable.day to Pair("day","diena"),
        R.drawable.winter to Pair("winter","žiema"),
        R.drawable.key to Pair("period","laikotarpį"),
        R.drawable.workday to Pair("workday","darbo diena"),
        R.drawable.weekend to Pair("weekend","savaitgalis"),
        R.drawable.twice to Pair("twice","du kartus"),
        R.drawable.once to Pair("once","vieną kartą"),
        R.drawable.week to Pair("week","savaitė"),
        R.drawable.tomorrow to Pair("tomorrow","rytoj"),
        R.drawable.today to Pair("Today","šiandien"),
        R.drawable.inafternoon to Pair("in the afternoon","po pietų"),
        R.drawable.early to Pair("early","anksti"),
        R.drawable.late to Pair("late","vėlai"),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),
//        R.drawable.key to Pair(),
//        R.drawable.sleep to Pair(),

    ).toList().take(4).shuffleList().toMap()
}

