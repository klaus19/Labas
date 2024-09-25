package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object TimeSingleton {

    val hashMapTime = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapTime["time"] = Triple("laikas", R.drawable.time, R.raw.time)
        hashMapTime["morning"] = Triple("rytas", R.drawable.morning, R.raw.morning)
        hashMapTime["day"] = Triple("diena", R.drawable.day, R.raw.day)
        hashMapTime["evening"] = Triple("vakaras", R.drawable.evening, R.raw.evening)
        hashMapTime["night"] = Triple("naktis", R.drawable.midnight, R.raw.night)
        hashMapTime["hour"] = Triple("valanda", R.drawable.hour, R.raw.hour)
        hashMapTime["today"] = Triple("šiandien", R.drawable.today, R.raw.today)
        hashMapTime["tomorrow"] = Triple("rytoj", R.drawable.tomorrow, R.raw.tomorrow)
        hashMapTime["yesterday"] = Triple("vakar", R.drawable.yesterday, R.raw.yesterday)
        hashMapTime["months"] = Triple("mėnesis", R.drawable.month, R.raw.months)

        hashMapTime["year"] = Triple("metai", R.drawable.year, R.raw.year)
        hashMapTime["week"] = Triple("savaitė", R.drawable.week, R.raw.week)
        hashMapTime["every day"] = Triple("kiekvieną dieną", R.drawable.everyday, R.raw.everyday)
        hashMapTime["What are you doing on Saturdays?"] = Triple("Ką tu veiki šeštadieniais?", R.drawable.saturday, R.raw.whatrudoingonsaturdays)
        hashMapTime["usually"] = Triple("įprastai", R.drawable.usually, R.raw.usually)
        hashMapTime["in the afternoon"] = Triple("po pietų", R.drawable.inafternoon, R.raw.intheafternoon)
        hashMapTime["a week day"] = Triple("savaitės diena", R.drawable.weekday, R.raw.aweekday)
        hashMapTime["holiday"] = Triple("šventė", R.drawable.holiday1, R.raw.holiday)
        hashMapTime["What time is it now?"] = Triple("kiek dabar valandų?", R.drawable.whattime1, R.raw.whattimeitisnow)
        hashMapTime["a quarter to 7"] = Triple("be penkiolikos 7.", R.drawable.quarter7, R.raw.quarterto7)

        hashMapTime["a quarter past 9"] = Triple("penkiolika po 9", R.drawable.past9, R.raw.quarterpast9)
        hashMapTime["Half"] = Triple("pusė", R.drawable.half, R.raw.half)
        hashMapTime["before 8 a.m"] = Triple("prieš 8 (aštuntą) val", R.drawable.emptyimage, R.raw.befor8am)
        hashMapTime["after 5 mins"] = Triple("po 5 min", R.drawable.emptyimage, R.raw.after5minutes)
        hashMapTime["to start"] = Triple("pradėti", R.drawable.start1, R.raw.tostart)
        hashMapTime["to finish"] = Triple("pabaigti", R.drawable.finish1, R.raw.tofinish)
        hashMapTime["a break"] = Triple("pertrauka", R.drawable.abreak1, R.raw.abreak)
        hashMapTime["one hour has 60 minutes"] = Triple("viena valanda turi 60 minučiu", R.drawable.minutes60, R.raw.onehour60seconds)
        hashMapTime["a timetable"] = Triple("tvarkaraštis", R.drawable.timetable, R.raw.timetable)
        hashMapTime["happy hours"] = Triple("laimės valandos", R.drawable.happyhours, R.raw.happyhours)

        hashMapTime["an event"] = Triple("renginis", R.drawable.event1, R.raw.event)
        hashMapTime["Future"] = Triple("ateitis", R.drawable.future, R.raw.future)
        hashMapTime["last year"] = Triple("praeitais metais", R.drawable.lastyear, R.raw.lastyear)
        hashMapTime["last month"] = Triple("praeitą mėnesį", R.drawable.lastmonth, R.raw.lastmonths)
        hashMapTime["vacation"] = Triple("atostogos", R.drawable.vacation1, R.raw.vacation)
        hashMapTime["registration time"] = Triple("registracijos laikas", R.drawable.registration, R.raw.registrationtime)
    }

}