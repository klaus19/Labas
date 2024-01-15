package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object PersonalitySingleton {

    val hashMapPersonality = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapPersonality["aggresive"] = Triple("agresyvus", R.drawable.aggresive, R.raw.computer)
        hashMapPersonality["reliable"] = Triple("patikimas", R.drawable.reliable, R.raw.computer)
        hashMapPersonality["unfriendly"] = Triple("nedraugiškas", R.drawable.unfriendly, R.raw.computer)
        hashMapPersonality["not good"] = Triple("negeras", R.drawable.notgood, R.raw.computer)
        hashMapPersonality["depressed"] = Triple("prislėgtas", R.drawable.depressed, R.raw.computer)
        hashMapPersonality["charming"] = Triple("žavus", R.drawable.charming, R.raw.computer)
        hashMapPersonality["unsafe"] = Triple("nesaugus", R.drawable.unsafe, R.raw.computer)
        hashMapPersonality["anxious person"] = Triple("nerimastingas žmogus", R.drawable.anxious, R.raw.computer)
        hashMapPersonality["childish"] = Triple("vaikiška", R.drawable.childish, R.raw.computer)
        hashMapPersonality["immature"] = Triple("nesubrendęs", R.drawable.immature, R.raw.computer)

        hashMapPersonality["sensitive"] = Triple("jautrus", R.drawable.sensitive, R.raw.computer)
        hashMapPersonality["introverted"] = Triple("intravertas", R.drawable.introverted, R.raw.computer)
        hashMapPersonality["Certainty"] = Triple("tikrumas", R.drawable.certainty, R.raw.computer)
        hashMapPersonality["stubborn"] = Triple("užsispyręs", R.drawable.stubborn, R.raw.computer)
        hashMapPersonality["dependent"] = Triple("priklausomas", R.drawable.dependent, R.raw.computer)
        hashMapPersonality["impatient"] = Triple("nekantrus", R.drawable.impatient1, R.raw.computer)
        hashMapPersonality["emotional"] = Triple("emocinis", R.drawable.emotional, R.raw.computer)
        hashMapPersonality["lazy"] = Triple("tinginys", R.drawable.lazy1, R.raw.computer)
        hashMapPersonality["dishonest"] = Triple("nesąžiningas", R.drawable.dishonest, R.raw.computer)
        hashMapPersonality["domineering"] = Triple("valdingas", R.drawable.ic_launcher_background, R.raw.computer)

        hashMapPersonality["generous"] = Triple("dosnus", R.drawable.generous, R.raw.computer)
        hashMapPersonality["patient"] = Triple("pacientas", R.drawable.patient, R.raw.computer)
        hashMapPersonality["attractive"] = Triple("patrauklus", R.drawable.attractive, R.raw.computer)
        hashMapPersonality["selfish"] = Triple("savanaudis", R.drawable.selfish, R.raw.computer)
        hashMapPersonality["smart"] = Triple("protingas", R.drawable.smart, R.raw.computer)
        hashMapPersonality["conversations"] = Triple("šnekus", R.drawable.conversations1, R.raw.computer)
        hashMapPersonality["creative"] = Triple("kūrybingi", R.drawable.creative, R.raw.computer)
        hashMapPersonality["friendly"] = Triple("draugiškas", R.drawable.friendly11, R.raw.computer)
        hashMapPersonality["imagination"] = Triple("vaizduotės", R.drawable.imagination, R.raw.computer)
        hashMapPersonality["calm person"] = Triple("ramus žmogus", R.drawable.calmperson, R.raw.computer)

        hashMapPersonality["shy"] = Triple("drovus", R.drawable.shy, R.raw.computer)
        hashMapPersonality["grumpy"] = Triple("paniuręs", R.drawable.grumpy, R.raw.computer)
        hashMapPersonality["love"] = Triple("meilus", R.drawable.love, R.raw.computer)
        hashMapPersonality["sincere"] = Triple("nuoširdus", R.drawable.sincere, R.raw.computer)
        hashMapPersonality["jealous"] = Triple("pavydus", R.drawable.jealous, R.raw.computer)
        hashMapPersonality["irresponsible"] = Triple("neatsakinga", R.drawable.irresponsible, R.raw.computer)
        hashMapPersonality["competitive"] = Triple("konkurencinga", R.drawable.competitive, R.raw.computer)
        hashMapPersonality["insensitive"] = Triple("nejautrus", R.drawable.insensitive, R.raw.computer)
        hashMapPersonality["extrovert"] = Triple("ekstravertas", R.drawable.extrovert, R.raw.computer)

        hashMapPersonality["ambitious"] = Triple("ambicingas", R.drawable.ambitious, R.raw.computer)
        hashMapPersonality["rebellious"] = Triple("maištingas", R.drawable.rebellious, R.raw.computer)
        hashMapPersonality["responsible"] = Triple("atsakingas", R.drawable.responsible, R.raw.computer)
        hashMapPersonality["smart"] = Triple("protinga", R.drawable.smart, R.raw.computer)
        hashMapPersonality["independent"] = Triple("nepriklausomas", R.drawable.independent, R.raw.computer)
        hashMapPersonality["stupid"] = Triple("kvailas", R.drawable.stupid, R.raw.computer)
    }
}