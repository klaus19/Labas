package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object DailyBasicSingleton {

//    val hashMap = HashMap<String, Triple<String, Int, Int>>()
//
//    init {
//        // Hashmap of strings that will shown on cardview front and back side
//
//        hashMap["Computer"] = Triple("Kompiuteris", R.drawable.computer1, R.raw.computer)
//        hashMap["screen"] = Triple("ekranas", R.drawable.screen, R.raw.screen)
//        hashMap["slot"] = Triple("lizdas", R.drawable.slot, R.raw.socket)
//        hashMap["Programmer"] = Triple("Programuotojas", R.drawable.programmer, R.raw.programmer)
//        hashMap["Interface"] = Triple("Sąsaja", R.drawable.interface1, R.raw.interface1)
//        hashMap["sent an email"] = Triple("Išsiuntė elektroninį laišką.", R.drawable.sentemail, R.raw.sentanemail)
//        hashMap["account"] = Triple("sąskaitą", R.drawable.account1, R.raw.account)
//        hashMap["network"] = Triple("tinklo", R.drawable.network, R.raw.network)
//        hashMap["retrieve"] = Triple("atkurti", R.drawable.retrieve, R.raw.retrieve)
//        hashMap["a backup copy"] = Triple("atsarginę kopiją", R.drawable.backupcopy, R.raw.backupcopy)
//        hashMap["record"] = Triple("įrašas", R.drawable.record, R.raw.record)
//        hashMap["Log out"] = Triple("Atsijungti", R.drawable.logout, R.raw.logout)
//        hashMap["to save"] = Triple("sutaupyti", R.drawable.tosave, R.raw.tosave)
//        hashMap["set-up"] = Triple("sąranka", R.drawable.setup, R.raw.setup)
//        hashMap["computer mouse"] = Triple("Kompiuterio pelė", R.drawable.computermouse, R.raw.computermouse)
//
//
//        hashMap["awake"] = Triple("budrus", R.drawable.awake, R.raw.sleep)
//        hashMap["Download"] = Triple("parsisiųsti", R.drawable.download, R.raw.sleep)
//        hashMap["Browse"] = Triple("naršyti", R.drawable.browse, R.raw.sleep)
//        hashMap["Comment"] = Triple("komentuoti", R.drawable.comment, R.raw.sleep)
//        hashMap["Log In"] = Triple("Prisijungti", R.drawable.login, R.raw.sleep)
//        hashMap["Social Networks"] = Triple("Socialiniai tinklai", R.drawable.socialnetworks, R.raw.sleep)
//        hashMap["Keyboard"] = Triple("klaviatūra", R.drawable.keyboard, R.raw.sleep)
//        hashMap["Laptop"] = Triple("Nešiojamas kompiuteris", R.drawable.laptop, R.raw.sleep)
//        hashMap["Share"] = Triple("Dalintis", R.drawable.share, R.raw.sleep)
//        hashMap["Desktop Computer"] = Triple("Stalinis kompiuteris", R.drawable.desktopcomputer, R.raw.sleep)
//        hashMap["Click"] = Triple("Spustelėkite", R.drawable.click, R.raw.sleep)
//        hashMap["Enable"] = Triple("Įjungti", R.drawable.enable, R.raw.sleep)
//        hashMap["Report"] = Triple("Pranešimas", R.drawable.report, R.raw.sleep)
//        hashMap["Message"] = Triple("pranešimą", R.drawable.message, R.raw.sleep)
//        hashMap["creep downwards"] = Triple("slinkti žemyn", R.drawable.creepdownwards, R.raw.sleep)
//        hashMap["Application"] = Triple("Taikymas", R.drawable.application, R.raw.sleep)
//        hashMap["scroll up"] = Triple("slinkite aukštyn", R.drawable.scrollup, R.raw.sleep)
//        hashMap["install"] = Triple("diegti", R.drawable.install, R.raw.sleep)
//        hashMap["Error"] = Triple("klaida", R.drawable.error, R.raw.sleep)
//        hashMap["antivirus"] = Triple("antivirusas", R.drawable.antivirus, R.raw.sleep)
//        hashMap["Connect to the internet"] = Triple("prisijungti prie interneto", R.drawable.connect2internet,
//            R.raw.sleep)
//
//        hashMap["data"] = Triple("duomenis", R.drawable.data, R.raw.data)
//        hashMap["Font"] = Triple("šriftas", R.drawable.font, R.raw.font)
//        hashMap["Video card"] = Triple("Vaizdo plokštė", R.drawable.videocard, R.raw.videocard)
//        hashMap["hard disk drive"] = Triple("kietasis diskas", R.drawable.harddisk, R.raw.harddiskdrive)
//        hashMap["an icon for"] = Triple("piktograma", R.drawable.iconfor, R.raw.aniconfor)
//        hashMap["internet"] = Triple("internetas", R.drawable.internet, R.raw.internet)
//        hashMap["Progress"] = Triple("progresas", R.drawable.progress, R.raw.progress)
//        hashMap["Operating System"] = Triple("Operacinė sistema", R.drawable.operationalsystem, R.raw.operatingsystem)
//        hashMap["Follow"] = Triple("sekite", R.drawable.follow, R.raw.follow)
//        hashMap["delete"] = Triple("Ištrinti", R.drawable.delete, R.raw.delete)
//        hashMap["Upload to"] = Triple("įkelti", R.drawable.upload, R.raw.upload)
//        hashMap["Pixels"] = Triple("pikselių", R.drawable.pixels, R.raw.pixels)
//        hashMap["Programme"] = Triple("Programa", R.drawable.programme, R.raw.program)
//        hashMap["send request"] = Triple("siųsti užklausą", R.drawable.sendrequest, R.raw.sendrequest)
//        hashMap["headphones"] = Triple("ausines", R.drawable.headphones, R.raw.headphones)
//        hashMap["Password"] = Triple("Slaptažodis", R.drawable.password, R.raw.password)
//        hashMap["Virus"] = Triple("virusas", R.drawable.virus, R.raw.virus)
//        hashMap["update"] = Triple("atnaujinti", R.drawable.update, R.raw.update)
//        hashMap["reduce"] = Triple("sumažinti", R.drawable.reduce, R.raw.reduce)
//        hashMap["increase"] = Triple("padidinti", R.drawable.increase, R.raw.increase)
//
//
//        hashMap["relocation"] = Triple("perkėlimas", R.drawable.relocation, R.raw.relocation)
//        hashMap["Voice message"] = Triple("Balso pranešimas", R.drawable.voicemessage, R.raw.voicemessage)
//        hashMap["microphone"] = Triple("mikrofonas", R.drawable.microphone, R.raw.microphone)
//        hashMap["Printed from"] = Triple("spausdinti", R.drawable.printedfrom, R.raw.printedfrom)
//        hashMap["reload"] = Triple("perkrauti", R.drawable.reload, R.raw.reload)
//        hashMap["page"] = Triple("puslapis", R.drawable.page, R.raw.page)
//        hashMap["Spam"] = Triple("šlamštas", R.drawable.spam, R.raw.spam)
//        hashMap["advertising"] = Triple("reklama", R.drawable.advertising, R.raw.advertising)
//        hashMap["unblock"] = Triple("atblokuoti", R.drawable.unblock, R.raw.unblock)
//        hashMap["transmission"] = Triple("užkrato pernešimas", R.drawable.transmission, R.raw.transmission)
//        hashMap["antenna"] = Triple("antena", R.drawable.antenna, R.raw.antenna)
//        hashMap["Controller"] = Triple("valdiklis", R.drawable.controller, R.raw.controller)
//
//    }

}