package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ActionsSingleton {

    val hashMapActions = HashMap<String,Triple<String,Int,Int>>()

    init {

        hashMapActions["listen music"] = Triple("klausytis muzikos", R.drawable.listenmusic, R.raw.computer)
        hashMapActions["write an email"] = Triple("rašyti el. laišką", R.drawable.writemail, R.raw.computer)
        hashMapActions["send a message"] = Triple("siųsti žinutę", R.drawable.mssage, R.raw.computer)
        hashMapActions["buy a ticket"] = Triple("pirkti bilietą", R.drawable.buyticket, R.raw.computer)
        hashMapActions["prepare dinner"] = Triple("ruošti vakarienę", R.drawable.preparedinner, R.raw.computer)
        hashMapActions["read a book"] = Triple("skaityti knygą", R.drawable.readbook, R.raw.computer)
        hashMapActions["buy food"] = Triple("pirkti maistą", R.drawable.buyfood, R.raw.computer)
        hashMapActions["wash hands"] = Triple("plaukite rankas", R.drawable.washhands, R.raw.computer)
        hashMapActions["make a coffee"] = Triple("pasidaryti kavos", R.drawable.makecoffee, R.raw.computer)
        hashMapActions["to think about it"] = Triple("galvoti apie tai", R.drawable.tothinkaboutit, R.raw.computer)

        hashMapActions["What do you want to do?"] = Triple("Ką tu nori veikti?", R.drawable.whatdoyouwant, R.raw.computer)
        hashMapActions["I love you"] = Triple("Aš jus myliu", R.drawable.iloveu, R.raw.computer)
        hashMapActions["Till next time!"] = Triple("Iki kito karto!", R.drawable.tillnexttime, R.raw.computer)
        hashMapActions["to forget"] = Triple("pamiršti", R.drawable.toforget, R.raw.computer)
        hashMapActions["to try"] = Triple("bandyti", R.drawable.totry, R.raw.computer)
        hashMapActions["to guess"] = Triple("spėti", R.drawable.guess, R.raw.computer)
        hashMapActions["to decide"] = Triple("nuspręsti", R.drawable.todecide, R.raw.computer)
        hashMapActions["to rent a car"] = Triple("išsinuomoti automobilį", R.drawable.rentcar, R.raw.computer)
        hashMapActions["to choose"] = Triple("rinktis", R.drawable.tochoose, R.raw.computer)
        hashMapActions["to skip"] = Triple("praleisti", R.drawable.skip, R.raw.computer)

        hashMapActions["to allow"] = Triple("leisti", R.drawable.allow, R.raw.computer)
        hashMapActions["to touch"] = Triple("liesti", R.drawable.totouch, R.raw.computer)
        hashMapActions["to book(reserve)"] = Triple("rezervuoti", R.drawable.reserve, R.raw.computer)
        hashMapActions["manage"] = Triple("tvarkyti", R.drawable.manage, R.raw.computer)
        hashMapActions["to show"] = Triple("parodyti", R.drawable.show1, R.raw.computer)
        hashMapActions["to complete"] = Triple("užpildyti", R.drawable.tocomplete, R.raw.computer)
        hashMapActions["to get"] = Triple("gauti", R.drawable.toget, R.raw.computer)
        hashMapActions["to steal"] = Triple("pavogti", R.drawable.tosteal, R.raw.computer)
        hashMapActions["update"] = Triple("atnaujinti", R.drawable.update, R.raw.computer)
        hashMapActions["to break"] = Triple("lūžti", R.drawable.tobreak, R.raw.computer)

        hashMapActions["to check"] = Triple("patikrinti", R.drawable.tocheck, R.raw.computer)
        hashMapActions["compare to ..."] = Triple("palyginti su ...", R.drawable.compare, R.raw.computer)
        hashMapActions["describe"] = Triple("apibūdinti", R.drawable.describe, R.raw.computer)
        hashMapActions["to rise"] = Triple("pakilti", R.drawable.torise, R.raw.computer)






    }

}