package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object PersonalitySingleton {

    val hashMapPersonality = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapPersonality["aggresive"] = Triple("agresyvus", R.drawable.aggresive, R.raw.aggresive)
        hashMapPersonality["reliable"] = Triple("patikimas", R.drawable.reliable, R.raw.reliable)
        hashMapPersonality["unfriendly"] = Triple("nedraugiškas", R.drawable.unfriendly, R.raw.unfriendly)
        hashMapPersonality["not good"] = Triple("negeras", R.drawable.notgood, R.raw.notgood)
        hashMapPersonality["depressed"] = Triple("prislėgtas", R.drawable.depressed, R.raw.depressed)
        hashMapPersonality["charming"] = Triple("žavus", R.drawable.charming, R.raw.charming)
        hashMapPersonality["unsafe"] = Triple("nesaugus", R.drawable.unsafe, R.raw.unsafe)
        hashMapPersonality["anxious person"] = Triple("nerimastingas žmogus", R.drawable.anxious, R.raw.anxiousperson)
        hashMapPersonality["childish"] = Triple("vaikiškas", R.drawable.childish, R.raw.childish)
        hashMapPersonality["immature"] = Triple("nesubrendęs", R.drawable.immature, R.raw.immature)

        hashMapPersonality["sensitive"] = Triple("jautrus", R.drawable.sensitive, R.raw.sensitive)
        hashMapPersonality["introverted"] = Triple("intravertas", R.drawable.introverted, R.raw.introverted)
        hashMapPersonality["Certainty"] = Triple("tikrumas", R.drawable.certainty, R.raw.certaintiy)
        hashMapPersonality["stubborn"] = Triple("užsispyręs", R.drawable.stubborn, R.raw.stubborn)
        hashMapPersonality["dependent"] = Triple("priklausomas", R.drawable.dependent, R.raw.dependent)
        hashMapPersonality["impatient"] = Triple("nekantrus", R.drawable.impatient1, R.raw.impatient)
        hashMapPersonality["emotional"] = Triple("emocinis", R.drawable.emotional, R.raw.emotional)
        hashMapPersonality["lazy"] = Triple("tinginys", R.drawable.lazy1, R.raw.lazy)
        hashMapPersonality["dishonest"] = Triple("nesąžiningas", R.drawable.dishonest, R.raw.dishonest)
        hashMapPersonality["domineering"] = Triple("valdingas", R.drawable.emptyimage, R.raw.domineering)

        hashMapPersonality["generous"] = Triple("dosnus", R.drawable.generous, R.raw.generous)
        hashMapPersonality["patient"] = Triple("pacientas", R.drawable.patient, R.raw.patient)
        hashMapPersonality["attractive"] = Triple("patrauklus", R.drawable.attractive, R.raw.attractive)
        hashMapPersonality["selfish"] = Triple("savanaudis", R.drawable.selfish, R.raw.selfish)
        hashMapPersonality["smart"] = Triple("protingas", R.drawable.smart, R.raw.smart)
        hashMapPersonality["conversations"] = Triple("pokalbiai", R.drawable.conversations1, R.raw.conversations)
        hashMapPersonality["creative"] = Triple("kūrybingi", R.drawable.creative, R.raw.creative)
        hashMapPersonality["friendly"] = Triple("draugiškas", R.drawable.friendly11, R.raw.friendly)
        hashMapPersonality["imagination"] = Triple("vaizduotė", R.drawable.imagination, R.raw.imagination)
        hashMapPersonality["calm person"] = Triple("ramus žmogus", R.drawable.calmperson, R.raw.calmperson)

        hashMapPersonality["shy"] = Triple("drovus", R.drawable.shy, R.raw.shy)
        hashMapPersonality["grumpy"] = Triple("paniuręs", R.drawable.grumpy, R.raw.grumpy)
        hashMapPersonality["love"] = Triple("meilė", R.drawable.love, R.raw.love)
        hashMapPersonality["sincere"] = Triple("nuoširdus", R.drawable.sincere, R.raw.sincere)
        hashMapPersonality["jealous"] = Triple("pavydus", R.drawable.jealous, R.raw.jealous)
        hashMapPersonality["irresponsible"] = Triple("neatsakinga", R.drawable.irresponsible, R.raw.irresponsible)
        hashMapPersonality["competitive"] = Triple("konkurencinga", R.drawable.competitive, R.raw.competive)
        hashMapPersonality["insensitive"] = Triple("nejautrus", R.drawable.insensitive, R.raw.insensitive)
        hashMapPersonality["extrovert"] = Triple("ekstravertas", R.drawable.extrovert, R.raw.extrovert)

        hashMapPersonality["ambitious"] = Triple("ambicingas", R.drawable.ambitious, R.raw.ambitious)
        hashMapPersonality["rebellious"] = Triple("maištingas", R.drawable.rebellious, R.raw.rebellious)
        hashMapPersonality["responsible"] = Triple("atsakingas", R.drawable.responsible, R.raw.responsible)
        hashMapPersonality["smart"] = Triple("protinga", R.drawable.smart, R.raw.smart)
        hashMapPersonality["independent"] = Triple("nepriklausomas", R.drawable.independent, R.raw.independent)
        hashMapPersonality["stupid"] = Triple("kvailas", R.drawable.stupid, R.raw.stupid)
    }
}