package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object CinemaSingleton {

    val hashMapCinemawords = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapCinemawords["stage"] = Triple("scena", R.drawable.stage, R.raw.stage)
        hashMapCinemawords["playwright"] = Triple("dramaturgas", R.drawable.playwright, R.raw.playwright)
        hashMapCinemawords["the villain"] = Triple("piktadarys", R.drawable.villain, R.raw.villian)
        hashMapCinemawords["Critic"] = Triple("kritikas", R.drawable.critic, R.raw.critic)
        hashMapCinemawords["dialogue"] = Triple("dialogas", R.drawable.dialogue, R.raw.dialog)
        hashMapCinemawords["actor"] = Triple("aktorius", R.drawable.actor, R.raw.actor)
        hashMapCinemawords["special effects"] = Triple("specialieji efektai", R.drawable.specialeffects, R.raw.specialeffect)
        hashMapCinemawords["mask"] = Triple("kaukė", R.drawable.mask, R.raw.mask)
        hashMapCinemawords["scary"] = Triple("baugus", R.drawable.scary, R.raw.scary)
        hashMapCinemawords["operator"] = Triple("operatorius", R.drawable.operator, R.raw.operator)

        hashMapCinemawords["The star"] = Triple("žvaigždė", R.drawable.star, R.raw.star)
        hashMapCinemawords["orchestra"] = Triple("orkestras", R.drawable.orchestra, R.raw.orchestra)
        hashMapCinemawords["Behind the scenes"] = Triple("užkulisiuose", R.drawable.emptyimage, R.raw.behindscenes)
        hashMapCinemawords["rehearsal"] = Triple("repeticija", R.drawable.rehearsel, R.raw.rehereal)
        hashMapCinemawords["science fiction"] = Triple("mokslinė fantastika", R.drawable.sciencefiction, R.raw.sciencefiction)
        hashMapCinemawords["western film"] = Triple("vakarietiškas filmas", R.drawable.westernfilm, R.raw.westernfilm)
        hashMapCinemawords["cloakroom"] = Triple("rūbinė", R.drawable.cloakroom, R.raw.cloakroom)
        hashMapCinemawords["choreographer"] = Triple("choreografas", R.drawable.choreographer, R.raw.choreographer)
        hashMapCinemawords["drama"] = Triple("drama", R.drawable.drama, R.raw.drama)
        hashMapCinemawords["historical film"] = Triple("istorinis filmas", R.drawable.historicalfilm, R.raw.historicalfilm)

        hashMapCinemawords["theater"] = Triple("kino teatras", R.drawable.theater, R.raw.theatre)
        hashMapCinemawords["based on the book"] = Triple("remiantis knyga", R.drawable.basedonthebook, R.raw.basedonthebook)
        hashMapCinemawords["The Musical"] = Triple("miuziklas", R.drawable.musical, R.raw.musical)
        hashMapCinemawords["translator"] = Triple("vertėjas", R.drawable.translator, R.raw.translaator)
        hashMapCinemawords["location"] = Triple("vieta", R.drawable.location, R.raw.location)
        hashMapCinemawords["Duplicate"] = Triple("dubliuoti", R.drawable.duplicate, R.raw.duplicate)
        hashMapCinemawords["costume"] = Triple("kostiumai", R.drawable.costume, R.raw.costume)
        hashMapCinemawords["Auditorium"] = Triple("auditorija", R.drawable.auditorium, R.raw.auditorium)
        hashMapCinemawords["subtitles"] = Triple("subtitrai", R.drawable.subtitles, R.raw.subtitles)
        hashMapCinemawords["Studio"] = Triple("studija", R.drawable.studio, R.raw.studio)

        hashMapCinemawords["actress"] = Triple("aktorė", R.drawable.actress, R.raw.actress)
        hashMapCinemawords["Genre"] = Triple("žanras", R.drawable.genre, R.raw.genre)
        hashMapCinemawords["thriller"] = Triple("trileris", R.drawable.thriller, R.raw.thriller)
        hashMapCinemawords["violence"] = Triple("smurtas", R.drawable.violence, R.raw.violence)
        hashMapCinemawords["horror film"] = Triple("siaubo filmas", R.drawable.horrorfilm, R.raw.horrorfilm)
        hashMapCinemawords["war film"] = Triple("karo filmas", R.drawable.warfilm, R.raw.warmfilm)
        hashMapCinemawords["romantic comedy"] = Triple("romantinė komedija", R.drawable.romanticcomedy, R.raw.romanticcomedy)
        hashMapCinemawords["Main character"] = Triple("Pagrindinis veikėjas", R.drawable.maincharacter, R.raw.maincharacter)
        hashMapCinemawords["action film"] = Triple("veiksmo filmas", R.drawable.actionfilm, R.raw.actionfilm)
        hashMapCinemawords["film"] = Triple("filmas", R.drawable.film, R.raw.film)

        hashMapCinemawords["checkout"] = Triple("kasą", R.drawable.checkout, R.raw.checkout)
        hashMapCinemawords["mesmerising film"] = Triple("įtaigus filmas", R.drawable.mesmerisingfilm, R.raw.mesmerisingfilm)
        hashMapCinemawords["comedy"] = Triple("komedija", R.drawable.comedy, R.raw.comedy)
        hashMapCinemawords["Movie"] = Triple("Filmas", R.drawable.movie, R.raw.movie)
        hashMapCinemawords["stuntman"] = Triple("kaskadininkas", R.drawable.stuntman, R.raw.stuntman)
        hashMapCinemawords["Producer"] = Triple("prodiuseris", R.drawable.producer, R.raw.producer)
        hashMapCinemawords["Listening"] = Triple("klausymas", R.drawable.listening, R.raw.listening)
    }



}