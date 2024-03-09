package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object ProfessionSingleton {

    val hashMapProfession = HashMap<String,Triple<String,Int,Int>>()

    init {
        hashMapProfession["teacher"] = Triple("mokytojas", R.drawable.teacher, R.raw.computer)
        hashMapProfession["doctor"] = Triple("gydytojas", R.drawable.doctor, R.raw.computer)
        hashMapProfession["seller"] = Triple("pardavėjas", R.drawable.seller, R.raw.computer)
        hashMapProfession["lawyer"] = Triple("teisininkas", R.drawable.lawyer, R.raw.computer)
        hashMapProfession["advocate"] = Triple("advokatas", R.drawable.advocate, R.raw.computer)
        hashMapProfession["priest"] = Triple("kunigas", R.drawable.priest, R.raw.computer)
        hashMapProfession["driver"] = Triple("vairuotojas", R.drawable.driver1, R.raw.computer)
        hashMapProfession["pensioner"] = Triple("pensininkas", R.drawable.pensioner, R.raw.computer)
        hashMapProfession["millionaire"] = Triple("milijonierius", R.drawable.millionaire, R.raw.computer)
        hashMapProfession["unemployed"] = Triple("bedarbiu", R.drawable.unemployed, R.raw.computer)

        hashMapProfession["farmer"] = Triple("ūkininkas", R.drawable.farmer, R.raw.computer)
        hashMapProfession["builder"] = Triple("statybininkas", R.drawable.builder, R.raw.computer)
        hashMapProfession["manager"] = Triple("vadybininkas/vadovas", R.drawable.manager, R.raw.computer)
        hashMapProfession["assistant"] = Triple("asistentas", R.drawable.assistant, R.raw.computer)
        hashMapProfession["engineer"] = Triple("inžinierius", R.drawable.engineer, R.raw.computer)
        hashMapProfession["reporter"] = Triple("reporteris", R.drawable.reporter, R.raw.computer)
        hashMapProfession["writer"] = Triple("rašytojas", R.drawable.writer, R.raw.computer)
        hashMapProfession["guard"] = Triple("apsauga", R.drawable.guard, R.raw.computer)
        hashMapProfession["plumber"] = Triple("santechnikas", R.drawable.plumber, R.raw.computer)
        hashMapProfession["hairdresser"] = Triple("kirpykla", R.drawable.hairdresser, R.raw.computer)

        hashMapProfession["banker"] = Triple("bankininkas", R.drawable.banker, R.raw.computer)
        hashMapProfession["politician"] = Triple("politikas", R.drawable.politician1, R.raw.computer)
        hashMapProfession["soldier"] = Triple("karys", R.drawable.soldier, R.raw.computer)
        hashMapProfession["worker"] = Triple("darbininkas", R.drawable.worker11, R.raw.computer)
        hashMapProfession["cook"] = Triple("virėjas", R.drawable.cook, R.raw.computer)
        hashMapProfession["pilot"] = Triple("pilotas", R.drawable.pilot, R.raw.computer)
        hashMapProfession["accountant"] = Triple("buhalteris", R.drawable.accountant, R.raw.computer)
        hashMapProfession["analyst"] = Triple("analitikas", R.drawable.analyst, R.raw.computer)
        hashMapProfession["founder"] = Triple("įkūrėjas", R.drawable.founder, R.raw.computer)
        hashMapProfession["storyteller"] = Triple("pasakotojas", R.drawable.storyteller, R.raw.computer)

        hashMapProfession["translator"] = Triple("vertėjas", R.drawable.translator, R.raw.computer)
        hashMapProfession["magician"] = Triple("magas", R.drawable.magician, R.raw.computer)
        hashMapProfession["artist"] = Triple("menininkas", R.drawable.artist, R.raw.computer)
        hashMapProfession["designer"] = Triple("dizaineris", R.drawable.designer, R.raw.computer)
        hashMapProfession["captain"] = Triple("kapitonas", R.drawable.captain, R.raw.computer)
        hashMapProfession["policeman"] = Triple("policininkas", R.drawable.policeman, R.raw.computer)
        hashMapProfession["waiter"] = Triple("padavėjas", R.drawable.waiter, R.raw.computer)
        hashMapProfession["gardener"] = Triple("sodininkas", R.drawable.gardener, R.raw.computer)
        hashMapProfession["scientist"] = Triple("mokslininkas", R.drawable.scientist, R.raw.computer)
        hashMapProfession["surgeon"] = Triple("chirurgas", R.drawable.surgeon, R.raw.computer)

        hashMapProfession["dentist"] = Triple("odontologas", R.drawable.dentist, R.raw.computer)
        hashMapProfession["student"] = Triple("studentas", R.drawable.student, R.raw.computer)
        hashMapProfession["professor"] = Triple("profesorius", R.drawable.professor, R.raw.computer)
        hashMapProfession["speaker"] = Triple("garsiakalbis", R.drawable.speaker, R.raw.computer)
        hashMapProfession["cleaner"] = Triple("švaresnis", R.drawable.cleaner, R.raw.computer)
        hashMapProfession["to work"] = Triple("dirbti", R.drawable.towork11, R.raw.computer)
        hashMapProfession["to teach"] = Triple("mokyti", R.drawable.toteach, R.raw.computer)
        hashMapProfession["a company"] = Triple("įmonė", R.drawable.company, R.raw.computer)
        hashMapProfession["businessman"] = Triple("verslininkas", R.drawable.businessman, R.raw.computer)

        hashMapProfession["a broker"] = Triple("tarpininkas", R.drawable.broker, R.raw.computer)
        hashMapProfession["a maid"] = Triple("kambarinė", R.drawable.maid, R.raw.computer)
        hashMapProfession["patient"] = Triple("pacientas", R.drawable.patient, R.raw.computer)
        hashMapProfession["Nurse"] = Triple("slaugytoja", R.drawable.nurse, R.raw.computer)
    }
}