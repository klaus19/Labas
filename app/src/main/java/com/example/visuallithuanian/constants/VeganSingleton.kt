package com.example.visuallithuanian.constants

import com.example.visuallithuanian.R

object VeganSingleton {

    val hashMapVegan = HashMap<String, Triple<String, Int, Int>>()
    
    init {
        hashMapVegan["A vegan (male/female)"] = Triple("Veganas / veganė", R.drawable.avegan, R.raw.vegan)
        hashMapVegan["An Animal"] = Triple("Gyvūnas", R.drawable.animal, R.raw.animal)
        hashMapVegan["A cage"] = Triple("Narvas", R.drawable.cage, R.raw.cage)
        hashMapVegan["Animal rights"] = Triple("Gyvūnų teisės", R.drawable.animalrights, R.raw.animalrights)
        hashMapVegan["Diet"] = Triple("Dieta", R.drawable.diet, R.raw.diet)
        hashMapVegan["Plant-based diet (nutrition)"] = Triple("Augalinė mityba", R.drawable.plants, R.raw.plantbaseddiet)
        hashMapVegan["Cow's milk"] = Triple("Karvės pienas", R.drawable.cowmilk, R.raw.cowmilk)
        hashMapVegan["Ethical vegan"] = Triple("Etinis veganas", R.drawable.ethicalvegan, R.raw.ethicalvegan)
        hashMapVegan["Of animal origin"] = Triple("Gyvūninės kilmės", R.drawable.animalorigin, R.raw.animalorigin)
        hashMapVegan["With soy milk"] = Triple("Su sojų pienu", R.drawable.soymilk, R.raw.soymilk)
        hashMapVegan["Coconut milk"] = Triple("Kokosų pienas", R.drawable.coconutmilk, R.raw.coconutmilk)
        hashMapVegan["A farm"] = Triple("Ferma", R.drawable.farm, R.raw.farm)
        hashMapVegan["Activism"] = Triple("Aktyvizmas", R.drawable.activism, R.raw.activism)
        hashMapVegan["An activist (male/female)"] = Triple("Aktyvistas / aktyvistė", R.drawable.activist, R.raw.activist)
        hashMapVegan["Pain"] = Triple("Skausmas", R.drawable.pain, R.raw.pain)
        hashMapVegan["A reason"] = Triple("Priežastis", R.drawable.reason, R.raw.reason)
        hashMapVegan["A taste"] = Triple("Skonis", R.drawable.taste, R.raw.taste)
        hashMapVegan["An alternative"] = Triple("Alternatyva", R.drawable.alternative, R.raw.alternative)
        hashMapVegan["Uninhabited island"] = Triple("Negyvenama sala", R.drawable.unhabitatedisland, R.raw.uninhibitedisland)
        hashMapVegan["Protein"] = Triple("Baltymai", R.drawable.protein, R.raw.protein)
        hashMapVegan["I love animals"] = Triple("Myliu gyvūnus", R.drawable.iloveanimals, R.raw.loveanimals)
        hashMapVegan["Cube of Truth"] = Triple("Tiesos kubas", R.drawable.cubetruth, R.raw.cubeoftruth)
    }
}