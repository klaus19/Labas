package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object HouseholdSingleton {

    val hashMapHousehold = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapHousehold["private"] = Triple("privatus", R.drawable.private1, R.raw.computer)
        hashMapHousehold["light-colored"] = Triple("šviesUs", R.drawable.lightcolored, R.raw.computer)
        hashMapHousehold["dark"] = Triple("tamsUs", R.drawable.dark, R.raw.computer)
        hashMapHousehold["quiet"] = Triple("tylUs", R.drawable.quiet1, R.raw.computer)
        hashMapHousehold["noisy"] = Triple("triukšmingas", R.drawable.noisy, R.raw.computer)
        hashMapHousehold["to have"] = Triple("turėti - turi - turejo", R.drawable.tohave, R.raw.computer)
        hashMapHousehold["to buy"] = Triple("pirkti - perka - pirko", R.drawable.tobuy1, R.raw.computer)
        hashMapHousehold["roof"] = Triple("stOgas", R.drawable.roof, R.raw.computer)
        hashMapHousehold["window"] = Triple("langas", R.drawable.window, R.raw.computer)
        hashMapHousehold["doors"] = Triple("durys", R.drawable.door1, R.raw.computer)

        hashMapHousehold["yard"] = Triple("kiemas", R.drawable.yard, R.raw.computer)
        hashMapHousehold["balcony"] = Triple("balkonas", R.drawable.balcony, R.raw.computer)
        hashMapHousehold["stairs"] = Triple("laiptai", R.drawable.stairs, R.raw.computer)
        hashMapHousehold["postbox"] = Triple("pašto dėžUtė", R.drawable.postbox, R.raw.computer)
        hashMapHousehold["ceiling"] = Triple("lubos", R.drawable.ceiling, R.raw.computer)
        hashMapHousehold["floor"] = Triple("grindys", R.drawable.floor, R.raw.computer)
        hashMapHousehold["wall"] = Triple("siena", R.drawable.wall, R.raw.computer)
        hashMapHousehold["kitchen"] = Triple("virtuve", R.drawable.kitchen, R.raw.computer)
        hashMapHousehold["dinner room"] = Triple("svetAinė", R.drawable.dinnerroom, R.raw.computer)
        hashMapHousehold["boiler-room"] = Triple("katilinė", R.drawable.boilerroom, R.raw.computer)

        hashMapHousehold["bath-room"] = Triple("voniA", R.drawable.bathroom, R.raw.computer)
        hashMapHousehold["arm-chair"] = Triple("fOtelis", R.drawable.armchair, R.raw.computer)
        hashMapHousehold["chair"] = Triple("kedė", R.drawable.chair, R.raw.computer)
        hashMapHousehold["table"] = Triple("stAlas", R.drawable.table, R.raw.computer)
        hashMapHousehold["double bed"] = Triple("dvigulė lova", R.drawable.doublebed, R.raw.computer)
        hashMapHousehold["single bed"] = Triple("viengulė lova", R.drawable.separatebeds, R.raw.computer)
        hashMapHousehold["shelf"] = Triple("lentyna", R.drawable.shelf, R.raw.computer)
        hashMapHousehold["furniture"] = Triple("baldai", R.drawable.furniture, R.raw.computer)
        hashMapHousehold["plants"] = Triple("augalas", R.drawable.plants, R.raw.computer)

        hashMapHousehold["sink"] = Triple("kriauklė", R.drawable.sink, R.raw.computer)
        hashMapHousehold["stove"] = Triple("viryklė", R.drawable.stove, R.raw.computer)
        hashMapHousehold["curtains"] = Triple("užuolaidos", R.drawable.curtains, R.raw.computer)
        hashMapHousehold["fabrics"] = Triple("audiniai", R.drawable.fabrics, R.raw.computer)
        hashMapHousehold["extra bed"] = Triple("papildoma lova", R.drawable.extrabed, R.raw.computer)
        hashMapHousehold["a room key"] = Triple("kambario raktas", R.drawable.roomkey, R.raw.computer)
        hashMapHousehold["storage"] = Triple("saugykla", R.drawable.storage, R.raw.computer)
        hashMapHousehold["heating"] = Triple("šildymas", R.drawable.heating, R.raw.computer)
        hashMapHousehold["fire"] = Triple("ugnis", R.drawable.fire, R.raw.computer)


    }



}