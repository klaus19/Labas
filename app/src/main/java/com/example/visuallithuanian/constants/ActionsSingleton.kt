package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ActionsSingleton {

    val hashMapActions = HashMap<String,Triple<String,Int,Int>>()

    init {

        hashMapActions["listen to music"] = Triple("klausytis muzikos", R.drawable.listenmusic, R.raw.listentomusic)
        hashMapActions["write an email"] = Triple("rašyti el. laišką", R.drawable.writemail, R.raw.writeanemail)
        hashMapActions["send a message"] = Triple("siųsti žinutę", R.drawable.mssage, R.raw.sendmessage)
        hashMapActions["buy a ticket"] = Triple("pirkti bilietą", R.drawable.buyticket, R.raw.buyticket)
        hashMapActions["prepare dinner"] = Triple("ruošti vakarienę", R.drawable.preparedinner, R.raw.preparedinner)
        hashMapActions["read a book"] = Triple("skaityti knygą", R.drawable.readbook, R.raw.readbook)
        hashMapActions["buy food"] = Triple("pirkti maistą", R.drawable.buyfood, R.raw.buyfood)
        hashMapActions["wash hands"] = Triple("plaukite rankas", R.drawable.washhands, R.raw.washhands)
        hashMapActions["make a coffee"] = Triple("pasidaryti kavos", R.drawable.makecoffee, R.raw.makeacoffee)
        hashMapActions["to think about it"] = Triple("galvoti apie tai", R.drawable.tothinkaboutit, R.raw.tothinkabout)

        hashMapActions["What do you want to do?"] = Triple("Ką tu nori veikti?", R.drawable.whatdoyouwant, R.raw.whatdoyouwanttodo)
        hashMapActions["I love you"] = Triple("Aš jus myliu", R.drawable.iloveu, R.raw.iloveu)
        hashMapActions["Till next time!"] = Triple("Iki kito karto!", R.drawable.tillnexttime, R.raw.tillnexttime)
        hashMapActions["to forget"] = Triple("pamiršti", R.drawable.toforget, R.raw.toforget)
        hashMapActions["to try"] = Triple("bandyti", R.drawable.totry, R.raw.totry)
        hashMapActions["to guess"] = Triple("spėti", R.drawable.guess, R.raw.toguess)
        hashMapActions["to decide"] = Triple("nuspręsti", R.drawable.todecide, R.raw.todecide)
        hashMapActions["to rent a car"] = Triple("išsinuomoti automobilį", R.drawable.rentcar, R.raw.torentcar)
        hashMapActions["to choose"] = Triple("rinktis", R.drawable.tochoose, R.raw.tochoose)
        hashMapActions["to skip"] = Triple("praleisti", R.drawable.skip, R.raw.toskip)

        hashMapActions["to allow"] = Triple("leisti", R.drawable.allow, R.raw.toallow)
        hashMapActions["to touch"] = Triple("liesti", R.drawable.totouch, R.raw.totouch)
        hashMapActions["to book(reserve)"] = Triple("rezervuoti", R.drawable.reserve, R.raw.tobook)
        hashMapActions["manage"] = Triple("tvarkyti", R.drawable.manage, R.raw.manage)
        hashMapActions["to show"] = Triple("parodyti", R.drawable.show1, R.raw.toshow)
        hashMapActions["to complete"] = Triple("užpildyti", R.drawable.tocomplete, R.raw.tocomplete)
        hashMapActions["to get"] = Triple("gauti", R.drawable.toget, R.raw.toget)
        hashMapActions["to steal"] = Triple("pavogti", R.drawable.tosteal, R.raw.tosteal)
        hashMapActions["update"] = Triple("atnaujinti", R.drawable.update, R.raw.update)
        hashMapActions["to break"] = Triple("lūžti", R.drawable.tobreak, R.raw.tobreak)

        hashMapActions["to check"] = Triple("patikrinti", R.drawable.tocheck, R.raw.tocheck)
        hashMapActions["compare to ..."] = Triple("palyginti su ...", R.drawable.compare, R.raw.compareto)
        hashMapActions["describe"] = Triple("apibūdinti", R.drawable.describe, R.raw.describe)
        hashMapActions["to rise"] = Triple("pakilti", R.drawable.torise, R.raw.torise)






    }

}