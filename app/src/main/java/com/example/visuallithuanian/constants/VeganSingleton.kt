package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object VeganSingleton {

    val hashMapVegan = HashMap<String, Triple<String, Int, Int>>()
    
    init {
        hashMapVegan["A vegan (male/female)"] = Triple("Veganas / veganė", R.drawable.avegan, R.raw.computer)
        hashMapVegan["An Animal"] = Triple("Gyvūnas", R.drawable.animal, R.raw.computer)
        hashMapVegan["A cage"] = Triple("Narvas", R.drawable.cage, R.raw.computer)
        hashMapVegan["Animal rights"] = Triple("Gyvūnų teisės", R.drawable.animalrights, R.raw.computer)
        hashMapVegan["Diet"] = Triple("Dieta", R.drawable.diet, R.raw.computer)
        hashMapVegan["Plant-based diet (nutrition)"] = Triple("Augalinė mityba", R.drawable.plants, R.raw.computer)
        hashMapVegan["Cow's milk"] = Triple("Karvės pienas", R.drawable.cowmilk, R.raw.computer)
        hashMapVegan["Ethical vegan"] = Triple("Etinis veganas", R.drawable.ethicalvegan, R.raw.computer)
        hashMapVegan["Of animal origin"] = Triple("Gyvūninės kilmės", R.drawable.animalorigin, R.raw.computer)
        hashMapVegan["With soy milk"] = Triple("Su sojų pienu", R.drawable.soymilk, R.raw.computer)
        hashMapVegan["Coconut milk"] = Triple("Kokosų pienas", R.drawable.coconutmilk, R.raw.computer)
        hashMapVegan["A farm"] = Triple("Ferma", R.drawable.farm, R.raw.computer)
        hashMapVegan["Activism"] = Triple("Aktyvizmas", R.drawable.activism, R.raw.computer)
        hashMapVegan["An activist (male/female)"] = Triple("Aktyvistas / aktyvistė", R.drawable.activist, R.raw.computer)
        hashMapVegan["Pain"] = Triple("Skausmas", R.drawable.pain, R.raw.computer)
        hashMapVegan["A reason"] = Triple("Priežastis", R.drawable.reason, R.raw.computer)
        hashMapVegan["A taste"] = Triple("Skonis", R.drawable.taste, R.raw.computer)
        hashMapVegan["An alternative"] = Triple("Alternatyva", R.drawable.alternative, R.raw.computer)
        hashMapVegan["Uninhabited island"] = Triple("Negyvenama sala", R.drawable.unhabitatedisland, R.raw.computer)
        hashMapVegan["Protein"] = Triple("Baltymai", R.drawable.protein, R.raw.computer)
        hashMapVegan["I love animals"] = Triple("Myliu gyvūnus", R.drawable.iloveanimals, R.raw.computer)
        hashMapVegan["Cube of Truth"] = Triple("Tiesos kubas", R.drawable.cubetruth, R.raw.computer)
    }
}