package com.example.visuallithuanian.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.visuallithuanian.R
import com.example.visuallithuanian.adapter.TabLayoutAdapter
import com.example.visuallithuanian.databinding.FirstScreenBinding
import com.example.visuallithuanian.viewModel.BottomNavigationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstScreen:AppCompatActivity() {

    val levels = arrayOf(
        "Easy",
        "Medium",
        "Hard"
    )

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
                    navController.navigate(R.id.settingsFragment)
                }
            }
            true

        }

    }


}