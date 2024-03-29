package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object PointersSingleton {

     val hashMapPointers = HashMap<String, Triple<String, Int, Int>>()

      init {

          hashMapPointers["I"] = Triple("Aš", R.drawable.i, R.raw.computer)
          hashMapPointers["You"] = Triple("Tu", R.drawable.you, R.raw.computer)
          hashMapPointers["He"] = Triple("Jis", R.drawable.he, R.raw.computer)
          hashMapPointers["She"] = Triple("Ji", R.drawable.she, R.raw.computer)
          hashMapPointers["We"] = Triple("Mes", R.drawable.we, R.raw.computer)
          hashMapPointers["You(many)"] = Triple("Jūs", R.drawable.you, R.raw.computer)
          hashMapPointers["they"] = Triple("jie", R.drawable.they, R.raw.computer)
          hashMapPointers["they(girls)"] = Triple("jos", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["my"] = Triple("mano", R.drawable.my, R.raw.computer)
          hashMapPointers["your"] = Triple("tavo", R.drawable.your, R.raw.computer)

          hashMapPointers["his"] = Triple("jo", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["Her"] = Triple("Jos", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["our"] = Triple("mūsų", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["their"] = Triple("jų", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["I am"] = Triple("Aš esu", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["you are"] = Triple("tu esi", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["we are"] = Triple("mes esame", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["you are"] = Triple("Jūs esate", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["he is"] = Triple("jis yra", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["she is"] = Triple("ji yra", R.drawable.ic_launcher_background, R.raw.computer)

          hashMapPointers["What is it? Who is it?"] = Triple("Kas tai?", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["What?(f)"] = Triple("Kokia?", R.drawable.what, R.raw.computer)
          hashMapPointers["what?(m)"] = Triple("koks?", R.drawable.whattime1, R.raw.computer)
          hashMapPointers["where?"] = Triple("kur?", R.drawable.where, R.raw.computer)
          hashMapPointers["when?"] = Triple("kada?", R.drawable.when1, R.raw.computer)
          hashMapPointers["why?"] = Triple("kodėl?", R.drawable.why, R.raw.computer)
          hashMapPointers["how many?"] = Triple("kiek?", R.drawable.how, R.raw.computer)
          hashMapPointers["why?"] = Triple("kam?", R.drawable.why, R.raw.computer)
          hashMapPointers["But"] = Triple("bet!", R.drawable.ic_launcher_background, R.raw.computer)

          hashMapPointers["this"] = Triple("tai", R.drawable.this1, R.raw.computer)
          hashMapPointers["that"] = Triple("kad", R.drawable.that, R.raw.computer)
          hashMapPointers["here"] = Triple("čia", R.drawable.here, R.raw.computer)
          hashMapPointers["there"] = Triple("ten", R.drawable.there, R.raw.computer)
          hashMapPointers["already"] = Triple("jau", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["yet"] = Triple("dar", R.drawable.ic_launcher_background, R.raw.computer)
          hashMapPointers["someone"] = Triple("kažkas", R.drawable.ic_launcher_background, R.raw.computer)
      }


}