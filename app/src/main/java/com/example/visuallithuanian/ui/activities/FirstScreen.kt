package com.example.visuallithuanian.ui.activities

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.LanguageAdapter
import com.example.visuallithuanian.databinding.FirstScreenBinding
import com.example.visuallithuanian.model.LanguageModel

class FirstScreen:AppCompatActivity() {

    lateinit var binding: FirstScreenBinding
    lateinit var gridView:GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen)

        binding = FirstScreenBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(binding.root)
        }

        gridView = findViewById(R.id.mainSpecimens)
        val languageModelArrayList:ArrayList<LanguageModel> = ArrayList()
        languageModelArrayList.add(LanguageModel("Vaisiai",R.drawable.fruits,"Fruits"))
        languageModelArrayList.add(LanguageModel("Gėlės",R.drawable.flowers,"Flowers"))
        languageModelArrayList.add(LanguageModel("Daržovės",R.drawable.vegetable,"Vegetables"))
        languageModelArrayList.add(LanguageModel("Architektūros",R.drawable.campus,"Architectures"))

        val languageAdapter = LanguageAdapter(this,languageModelArrayList)
        gridView.adapter=languageAdapter
    }


}