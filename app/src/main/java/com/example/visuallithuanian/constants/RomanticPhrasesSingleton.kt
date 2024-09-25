package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object RomanticPhrasesSingleton {

    val hashMapRomanticPhrases = HashMap<String, Triple<String, Int, Int>>()

    init {
        hashMapRomanticPhrases["Your smile"] = Triple("Tavo šypsena", R.drawable.smile, R.raw.ursmile)
        hashMapRomanticPhrases["Your eyes"] = Triple("Tavo akys", R.drawable.eyes, R.raw.ureyes)
        hashMapRomanticPhrases["I want to hug you "] = Triple("Noriu tave apkabinti", R.drawable.hug, R.raw.iwant2hugu)
        hashMapRomanticPhrases["My dear(to address a girl)"] = Triple("Mano brangioji", R.drawable.mydear, R.raw.mydear)
        hashMapRomanticPhrases["My dear(to address a man)"] = Triple("Mano brangusis", R.drawable.mydearboy, R.raw.mydear1)
        hashMapRomanticPhrases["I appreciate you very much"] = Triple("Aš labai tave vertinu", R.drawable.appreciate, R.raw.iappreciateu)
        hashMapRomanticPhrases["I want to be with you"] = Triple("Noriu būti su tavimi", R.drawable.withyou, R.raw.iwanttobwithu)
        hashMapRomanticPhrases["Forever"] = Triple("Amžinai", R.drawable.forever, R.raw.forever)
        hashMapRomanticPhrases["Our connection is very strong"] = Triple("Mūsų ryšys labai stiprus", R.drawable.connection, R.raw.ourconnection)
        hashMapRomanticPhrases["You are very important to me"] = Triple("Tu man labai svarbus / svarbi", R.drawable.important1, R.raw.urveryimportant2me)
        hashMapRomanticPhrases["To kiss"] = Triple("Bučiuoti", R.drawable.kiss, R.raw.tokiss)
        hashMapRomanticPhrases["You're amazing"] = Triple("Tu nuostabus", R.drawable.amazing1, R.raw.uramazing)
        hashMapRomanticPhrases["You are amazing"] = Triple("Tu nuostabi", R.drawable.amazing2, R.raw.uramazing1)
        hashMapRomanticPhrases["Stars"] = Triple("Žvaigždės", R.drawable.stars, R.raw.stars)
        hashMapRomanticPhrases["Little things"] = Triple("Maži dalykai", R.drawable.littlethings, R.raw.littlethings)
        hashMapRomanticPhrases["It feels good to be with you"] = Triple("Man gera būti su tavimi", R.drawable.tobewithyou, R.raw.itfeelsgoodtobwithu)
        hashMapRomanticPhrases["To make love"] = Triple("Mylėtis", R.drawable.love, R.raw.tomakelove)
        hashMapRomanticPhrases["Hot"] = Triple("Karštai", R.drawable.hot, R.raw.hot)
        hashMapRomanticPhrases["Close"] = Triple("Artimas / artima", R.drawable.tobeclose, R.raw.close1)
        hashMapRomanticPhrases["To be yourself"] = Triple("Būti savimi", R.drawable.yourself, R.raw.tobeyourself)
        hashMapRomanticPhrases["To make someone laugh"] = Triple("Prajuokinti", R.drawable.tolaugh1, R.raw.tomakesomeonelaugh)
        hashMapRomanticPhrases["It's fun with you"] = Triple("Linksma su tavimi", R.drawable.`fun`, R.raw.itsfunwithu)
        hashMapRomanticPhrases["You are very good (kind)"] = Triple("Tu labai geras / gera", R.drawable.kind, R.raw.urverygood)
        hashMapRomanticPhrases["Always"] = Triple("Visada", R.drawable.always, R.raw.always)
        hashMapRomanticPhrases["You inspire me "] = Triple("Tu mane įkvepi", R.drawable.inspire, R.raw.uinspireme)
        hashMapRomanticPhrases["To meet"] = Triple("Susitikti", R.drawable.meet, R.raw.tomeet)
        hashMapRomanticPhrases["To drive one crazy"] = Triple("Vesti iš proto", R.drawable.crazy, R.raw.todriveonecrazy)
        hashMapRomanticPhrases["To trust"] = Triple("Pasitikėti", R.drawable.trust, R.raw.totrust)
        hashMapRomanticPhrases["To promise"] = Triple("Pažadėti", R.drawable.promise, R.raw.topromise)
        hashMapRomanticPhrases["Heart"] = Triple("Širdis", R.drawable.heart, R.raw.heart)
        hashMapRomanticPhrases["To stay silent"] = Triple("Tylėti", R.drawable.silent, R.raw.tostaysilent)
        hashMapRomanticPhrases["A dream"] = Triple("Svajonė", R.drawable.dream1, R.raw.adream)
        hashMapRomanticPhrases["To dream (in a sleep)"] = Triple("Sapnuoti", R.drawable.dream2, R.raw.todream)
        hashMapRomanticPhrases["To dream (not in a sleep)"] = Triple("Svajoti", R.drawable.dream3, R.raw.todream1)
        hashMapRomanticPhrases["A (night) dream"] = Triple("Sapnas", R.drawable.dream4, R.raw.adream1)
    }

}