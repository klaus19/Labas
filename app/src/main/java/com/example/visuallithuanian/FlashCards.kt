package com.example.visuallithuanian

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.visuallithuanian.adapter.ViewPagerAdapter
import com.example.visuallithuanian.ui.activities.FirstScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class FlashCards : Fragment() {

    private lateinit var bottomNav:BottomNavigationView

    private lateinit var viewPager:ViewPager2
    private lateinit var tabLayout:TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_flash_cards, container, false)

        //Taking the bOTTOMNavigation view instance from Activity into Fragment
        bottomNav = (activity as? FirstScreen)?.findViewById(R.id.bottomNavigationView)!!
        bottomNav.visibility = View.INVISIBLE

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        tabLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bottomNavigation))


        setupViewPagerAndTabs()

        return view
    }

    //This is used to setup the TabLayout with viewPager
    private fun setupViewPagerAndTabs() {

        // Create an adapter for the view pager
        val adapter = ViewPagerAdapter(this)

        // Set the adapter to the view pager
        viewPager.adapter = adapter

        // Attach the view pager to the tab layout
        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            when (position) {
                0 -> {
                    tab.text = "Saved"
                }

                1 -> tab.text = "Cards"
            }

        }.attach()

    }


}