package com.example.visuallithuanian.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.visuallithuanian.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstScreen:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        //Created a NavHost Controller

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNav.setupWithNavController(navController)
        //map the menu items
        bottomNav.setOnNavigationItemSelectedListener{item ->
            if (item.isChecked) {
                return@setOnNavigationItemSelectedListener false
            }
            when (item.itemId) {
                R.id.page_1 -> {
                    // handle item1 selection
                    navController.navigate(R.id.userFragment)
                }
                R.id.page_2 -> {
                    // handle item2 selection
                    navController.navigate(R.id.practiseFragment)
                }
                R.id.page_3 ->{
                    // handle item3 selection
                    navController.navigate(R.id.flashCards)
                }
                R.id.page_4 ->{
                    //handle item4 selection
                    navController.navigate(R.id.settingsFragment)
                }
            }
            true

        }

    }


}