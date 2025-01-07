package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object PointersSingleton {

     val hashMapPointers = HashMap<String, Triple<String, Int, Int>>()

      init {

          hashMapPointers["I"] = Triple("Aš", R.drawable.i, R.raw.i)
         hashMapPointers["You"] = Triple("Tu", R.drawable.you, R.raw.you)
          hashMapPointers["He"] = Triple("Jis", R.drawable.he, R.raw.he)
          hashMapPointers["She"] = Triple("Ji", R.drawable.she, R.raw.she)
          hashMapPointers["We"] = Triple("Mes", R.drawable.we, R.raw.we)
          hashMapPointers["What is it?"] = Triple("Kas tai?", R.drawable.emptyimage, R.raw.whatisit_)
          hashMapPointers["where?"] = Triple("kur?", R.drawable.where, R.raw.where)
          hashMapPointers["when?"] = Triple("kada?", R.drawable.when1, R.raw.whenn)
          hashMapPointers["why?"] = Triple("kodėl?", R.drawable.why, R.raw.why)
          hashMapPointers["But"] = Triple("bet!", R.drawable.emptyimage, R.raw.but)
          hashMapPointers["they"] = Triple("jie", R.drawable.they, R.raw.they)
          hashMapPointers["they(girls)"] = Triple("jos", R.drawable.theygirls, R.raw.they1jos)
          hashMapPointers["my"] = Triple("mano", R.drawable.my, R.raw.my)
          hashMapPointers["your"] = Triple("tavo", R.drawable.your, R.raw.your)
          hashMapPointers["his"] = Triple("jo", R.drawable.his, R.raw.his)
          hashMapPointers["Hers"] = Triple("Jos", R.drawable.her, R.raw.hers)
          hashMapPointers["our"] = Triple("mūsų", R.drawable.our, R.raw.our)
          hashMapPointers["I am"] = Triple("Aš esu", R.drawable.iam, R.raw.iam)
          hashMapPointers["you are"] = Triple("tu esi", R.drawable.youare, R.raw.youare)
          hashMapPointers["we are"] = Triple("mes esame", R.drawable.weare, R.raw.weare)
          hashMapPointers["he is"] = Triple("jis yra", R.drawable.heis, R.raw.heis)
          hashMapPointers["she is"] = Triple("ji yra", R.drawable.sheis, R.raw.sheis)
          hashMapPointers["What?(f)"] = Triple("Kokia?", R.drawable.what, R.raw.whatkokia)
          hashMapPointers["what?(m)"] = Triple("koks?", R.drawable.whattime1, R.raw.whatkoks)
          hashMapPointers["how many?"] = Triple("kiek?", R.drawable.how, R.raw.howmany)
          hashMapPointers["this"] = Triple("tai", R.drawable.this1, R.raw.this1)
          hashMapPointers["here"] = Triple("čia", R.drawable.here, R.raw.here)
          hashMapPointers["there"] = Triple("ten", R.drawable.there, R.raw.there)
          hashMapPointers["already"] = Triple("jau", R.drawable.emptyimage, R.raw.already)
          hashMapPointers["yet"] = Triple("dar", R.drawable.emptyimage, R.raw.yet)
          hashMapPointers["someone"] = Triple("kažkas", R.drawable.emptyimage, R.raw.someone)
      }


}