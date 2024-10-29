package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object HouseholdSingleton {

    val hashMapHousehold = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapHousehold["private"] = Triple("privatus", R.drawable.private1, R.raw.private1)
        hashMapHousehold["light-colored"] = Triple("šviesUs", R.drawable.lightcolored, R.raw.lighcolored)
        hashMapHousehold["dark"] = Triple("tamsUs", R.drawable.dark, R.raw.dark)
        hashMapHousehold["quiet"] = Triple("tylUs", R.drawable.quiet1, R.raw.quiet)
        hashMapHousehold["noisy"] = Triple("triukšmingas", R.drawable.noisy, R.raw.noisy)
        hashMapHousehold["to have"] = Triple("turėti - turi - turejo", R.drawable.tohave, R.raw.tohave)
        hashMapHousehold["to buy"] = Triple("pirkti - perka - pirko", R.drawable.tobuy1, R.raw.tobuy)
        hashMapHousehold["roof"] = Triple("stOgas", R.drawable.roof, R.raw.roof)
        hashMapHousehold["window"] = Triple("langas", R.drawable.window, R.raw.window)
        hashMapHousehold["doors"] = Triple("durys", R.drawable.door1, R.raw.doors)
        hashMapHousehold["yard"] = Triple("kiemas", R.drawable.yard, R.raw.yard)
        hashMapHousehold["balcony"] = Triple("balkonas", R.drawable.balcony, R.raw.balcony)
        hashMapHousehold["stairs"] = Triple("laiptai", R.drawable.stairs, R.raw.stairs)
        hashMapHousehold["postbox"] = Triple("pašto dėžUtė", R.drawable.postbox, R.raw.postbox)
        hashMapHousehold["ceiling"] = Triple("lubos", R.drawable.ceiling, R.raw.ceiling)
        hashMapHousehold["floor"] = Triple("grindys", R.drawable.floor, R.raw.floor)
        hashMapHousehold["wall"] = Triple("siena", R.drawable.wall, R.raw.wall)
        hashMapHousehold["kitchen"] = Triple("virtuve", R.drawable.kitchen, R.raw.kitchen)
        hashMapHousehold["dinner room"] = Triple("svetAinė", R.drawable.dinnerroom, R.raw.dinnerroom)
        hashMapHousehold["boiler-room"] = Triple("katilinė", R.drawable.boilerroom, R.raw.boilerroom)
        hashMapHousehold["bath-room"] = Triple("voniA", R.drawable.bathroom, R.raw.bahroom)
        hashMapHousehold["arm-chair"] = Triple("fOtelis", R.drawable.armchair, R.raw.armchair)
        hashMapHousehold["chair"] = Triple("kedė", R.drawable.chair, R.raw.chair)
        hashMapHousehold["table"] = Triple("stAlas", R.drawable.table, R.raw.table)
        hashMapHousehold["double bed"] = Triple("dvigulė lova", R.drawable.doublebed, R.raw.doublebed)
        hashMapHousehold["single bed"] = Triple("viengulė lova", R.drawable.separatebeds, R.raw.singlebed)
        hashMapHousehold["shelf"] = Triple("lentyna", R.drawable.shelf, R.raw.shelf)
        hashMapHousehold["furniture"] = Triple("baldai", R.drawable.furniture, R.raw.furniture)
        hashMapHousehold["plants"] = Triple("augalas", R.drawable.plants, R.raw.plants)
        hashMapHousehold["sink"] = Triple("kriauklė", R.drawable.sink, R.raw.sink)
        hashMapHousehold["stove"] = Triple("viryklė", R.drawable.stove, R.raw.stove)
        hashMapHousehold["curtains"] = Triple("užuolaidos", R.drawable.curtains, R.raw.curtains)
        hashMapHousehold["fabrics"] = Triple("audiniai", R.drawable.fabrics, R.raw.fabrics)
        hashMapHousehold["extra bed"] = Triple("papildoma lova", R.drawable.extrabed, R.raw.extrabed)
        hashMapHousehold["a room key"] = Triple("kambario raktas", R.drawable.roomkey, R.raw.roomkey)
        hashMapHousehold["storage"] = Triple("saugykla", R.drawable.storage, R.raw.storage)
        hashMapHousehold["heating"] = Triple("šildymas", R.drawable.heating, R.raw.heating)
        hashMapHousehold["fire"] = Triple("ugnis", R.drawable.fire, R.raw.fire)
    }



}