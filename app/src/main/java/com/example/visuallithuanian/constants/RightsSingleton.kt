package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object RightsSingleton {

    val hashMapRights = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapRights["clues"] = Triple("įkalčiai", R.drawable.clue, R.raw.computer)
        hashMapRights["summons"] = Triple("teismo šaukimas", R.drawable.summons, R.raw.computer)
        hashMapRights["false testimony"] = Triple("melagingų parodymų", R.drawable.falsetestimony, R.raw.computer)
        hashMapRights["lawyer"] = Triple("advokatas", R.drawable.lawyer, R.raw.computer)
        hashMapRights["Sue"] = Triple("iškelti bylą", R.drawable.sue, R.raw.computer)
        hashMapRights["civil law"] = Triple("civilinis įstatymas", R.drawable.civillaw, R.raw.computer)
        hashMapRights["advisor"] = Triple("patarėjas", R.drawable.advisor, R.raw.computer)
        hashMapRights["counterfeiting"] = Triple("klastotė", R.drawable.counterfeiting, R.raw.computer)
        hashMapRights["convicted"] = Triple("nuteistasis", R.drawable.convicted, R.raw.computer)
        hashMapRights["contract"] = Triple("sutartis", R.drawable.contract, R.raw.computer)

        hashMapRights["judicial court"] = Triple("teisminis teismas", R.drawable.judicialcourt, R.raw.computer)
        hashMapRights["defendant"] = Triple("atsakovas", R.drawable.defendent, R.raw.computer)
        hashMapRights["guilty"] = Triple("kaltas", R.drawable.guilty, R.raw.computer)
        hashMapRights["judge"] = Triple("teisėjas", R.drawable.judge, R.raw.computer)
        hashMapRights["case"] = Triple("atveju", R.drawable.case1, R.raw.computer)
        hashMapRights["jury"] = Triple("žiuri", R.drawable.jury, R.raw.computer)
        hashMapRights["action"] = Triple("ieškinį", R.drawable.action, R.raw.computer)
        hashMapRights["arson"] = Triple("padegimas", R.drawable.arson, R.raw.computer)
        hashMapRights["parole"] = Triple("lygtinis paleidimas", R.drawable.parole, R.raw.computer)
        hashMapRights["prosecutor"] = Triple("prokuroras", R.drawable.prosecutor, R.raw.computer)

        hashMapRights["to summarize"] = Triple("apibendrinti", R.drawable.summarize, R.raw.computer)
        hashMapRights["sentence"] = Triple("nuosprendis", R.drawable.sentence, R.raw.computer)
        hashMapRights["mortgage"] = Triple("hipoteka", R.drawable.mortgage, R.raw.computer)
        hashMapRights["arrested"] = Triple("areštuoti", R.drawable.arrested, R.raw.computer)
        hashMapRights["death penalty"] = Triple("mirties bausmė", R.drawable.deathpenalty, R.raw.computer)
        hashMapRights["criminal law"] = Triple("baudžiamasis įstatymas", R.drawable.criminallaw, R.raw.computer)
        hashMapRights["defender"] = Triple("gynėjas", R.drawable.defender, R.raw.computer)
        hashMapRights["illegal"] = Triple("neteisėtas", R.drawable.illegal, R.raw.computer)
        hashMapRights["innocent"] = Triple("nekaltas", R.drawable.innocent, R.raw.computer)
        hashMapRights["crime"] = Triple("nusikaltimas", R.drawable.crime, R.raw.computer)

        hashMapRights["to testify"] = Triple("liudyti", R.drawable.totestify, R.raw.computer)
        hashMapRights["murder"] = Triple("žmogžudystė", R.drawable.murder, R.raw.computer)
        hashMapRights["coercion"] = Triple("prievarta", R.drawable.coercion, R.raw.computer)
        hashMapRights["negligence"] = Triple("aplaidumas", R.drawable.negligence, R.raw.computer)
        hashMapRights["robbery"] = Triple("apiplėšimas", R.drawable.robbery, R.raw.computer)
        hashMapRights["vandalism"] = Triple("vandalizmas", R.drawable.vandalism, R.raw.computer)
        hashMapRights["to fulfill"] = Triple("įvykdyti", R.drawable.tofulfill, R.raw.computer)
        hashMapRights["legally"] = Triple("legaliai", R.drawable.legally, R.raw.computer)

        hashMapRights["amnesty"] = Triple("amnestija", R.drawable., R.raw.computer)
        hashMapRights["to appeal"] = Triple("apskųsti", R.drawable., R.raw.computer)
        hashMapRights["without laws"] = Triple("be įstatymų", R.drawable., R.raw.computer)
        hashMapRights["rape"] = Triple("išžaginimas", R.drawable., R.raw.computer)
        hashMapRights["attack"] = Triple("užpuolimas", R.drawable., R.raw.computer)
        hashMapRights["to confirm"] = Triple("patvirtinti", R.drawable., R.raw.computer)
        hashMapRights["security deposit"] = Triple("užstatas", R.drawable., R.raw.computer)
        hashMapRights["be under house arrest"] = Triple("būti namų arešte", R.drawable., R.raw.computer)
        hashMapRights["bankruptcy"] = Triple("bankrotas", R.drawable., R.raw.computer)
        hashMapRights["corruption"] = Triple("korupcija", R.drawable., R.raw.computer)

        hashMapRights["expense account"] = Triple("išlaidų sąskaita", R.drawable., R.raw.computer)
        hashMapRights["bonds"] = Triple("obligacijos", R.drawable., R.raw.computer)
        hashMapRights["tax"] = Triple("mokesčio", R.drawable., R.raw.computer)
        hashMapRights["child abuse"] = Triple("prievartą prieš vaiką", R.drawable., R.raw.computer)
        hashMapRights["foster children"] = Triple("globojamų vaikų", R.drawable., R.raw.computer)
        hashMapRights["danger"] = Triple("pavojus", R.drawable., R.raw.computer)
        
    }
}