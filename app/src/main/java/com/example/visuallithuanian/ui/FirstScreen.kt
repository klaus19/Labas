package com.example.visuallithuanian.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.visuallithuanian.R
import com.example.visuallithuanian.databinding.FirstScreenBinding

class FirstScreen:AppCompatActivity() {

    lateinit var binding: FirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen)

        binding = FirstScreenBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(binding.root)
        }



    }


}