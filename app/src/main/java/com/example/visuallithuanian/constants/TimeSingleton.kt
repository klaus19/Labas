package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object TimeSingleton {

    val hashMapTime = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapTime["time"] = Triple("laikas", R.drawable.time, R.raw.computer)
        hashMapTime["morning"] = Triple("rytas", R.drawable.morning, R.raw.computer)
        hashMapTime["day"] = Triple("diena", R.drawable.day, R.raw.computer)
        hashMapTime["evening"] = Triple("vakaras", R.drawable.evening, R.raw.computer)
        hashMapTime["night"] = Triple("naktis", R.drawable.midnight, R.raw.computer)
        hashMapTime["hour"] = Triple("valanda", R.drawable.hour, R.raw.computer)
        hashMapTime["today"] = Triple("šiandien", R.drawable.today, R.raw.computer)
        hashMapTime["tomorrow"] = Triple("rytoj", R.drawable.tomorrow, R.raw.computer)
        hashMapTime["yesterday"] = Triple("vakar", R.drawable.yesterday, R.raw.computer)
        hashMapTime["month"] = Triple("mėnesį", R.drawable.month, R.raw.computer)

        hashMapTime["year"] = Triple("metus", R.drawable.year, R.raw.computer)
        hashMapTime["week"] = Triple("savaite", R.drawable.week, R.raw.computer)
        hashMapTime["every day"] = Triple("kiekvieną dieną", R.drawable.everyday, R.raw.computer)
        hashMapTime["What are you doing on Saturdays?"] = Triple("Ką tu veiki šeštadieniais?", R.drawable.saturday, R.raw.computer)
        hashMapTime["usually"] = Triple("paprastai", R.drawable.usually, R.raw.computer)
        hashMapTime["in the afternoon"] = Triple("po pietų", R.drawable.inafternoon, R.raw.computer)
        hashMapTime["a week day"] = Triple("savaitės diena", R.drawable.weekday, R.raw.computer)
        hashMapTime["holiday"] = Triple("šventė", R.drawable.holiday1, R.raw.computer)
        hashMapTime["What time is it now?"] = Triple("kiek dabar valandų?", R.drawable.whattime1, R.raw.computer)
        hashMapTime["a quarter to 7."] = Triple("be penkiolika 7.", R.drawable.quarter7, R.raw.computer)

        hashMapTime["a quarter past 9."] = Triple("penkiolika po 9.", R.drawable.past9, R.raw.computer)
        hashMapTime["Half"] = Triple("pusė", R.drawable.half, R.raw.computer)
        hashMapTime["before 8 a.m."] = Triple("prieš 8 (aštuntą) val.", R.drawable.ic_launcher_background, R.raw.computer)
        hashMapTime["after 5 mins"] = Triple("po 5 min", R.drawable.ic_launcher_background, R.raw.computer)
        hashMapTime["to start"] = Triple("pradėti", R.drawable.start1, R.raw.computer)
        hashMapTime["to finish"] = Triple("pabaigti", R.drawable.finish1, R.raw.computer)
        hashMapTime["a break"] = Triple("poilsis", R.drawable.abreak1, R.raw.computer)
        hashMapTime["one hour has 60 minutes"] = Triple("viena valanda turi 60 (šešiadešimt) minučiu", R.drawable.minutes60, R.raw.computer)
        hashMapTime["a timetable"] = Triple("tvarkaraštis", R.drawable.timetable, R.raw.computer)
        hashMapTime["happy hours"] = Triple("laimės valandos", R.drawable.happyhours, R.raw.computer)

        hashMapTime["an event"] = Triple("renginis", R.drawable.event1, R.raw.computer)
        hashMapTime["Future"] = Triple("ateitis", R.drawable.future, R.raw.computer)
        hashMapTime["last year"] = Triple("praeitais metais", R.drawable.lastyear, R.raw.computer)
        hashMapTime["last month"] = Triple("praeitą mėnesį", R.drawable.lastmonth, R.raw.computer)
        hashMapTime["vacation"] = Triple("atostogos", R.drawable.vacation1, R.raw.computer)
        hashMapTime["registration time"] = Triple("registracijos laikas", R.drawable.registration, R.raw.computer)
    }

}