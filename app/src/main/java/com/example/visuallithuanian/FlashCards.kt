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
import com.example.visuallithuanian.ui.activities.fragments.CardsFragment
import com.example.visuallithuanian.ui.activities.fragments.SavedFragment
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
        bottomNav.visibility = View.VISIBLE

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        setupViewPagerAndTabs()

        return view
    }
    //This is used to setup the TabLayout with viewPager
    private fun setupViewPagerAndTabs() {

        val adapter = ViewPagerAdapter(this)

        adapter.addFragment(SavedFragment(), "Saved")
        adapter.addFragment(CardsFragment(), "Cards")

        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        // change the color of the text
        tabLayout.setTabTextColors(ContextCompat.getColor(requireContext(), android.R.color.white),
            ContextCompat.getColor(requireContext(), R.color.gridview_color))
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(requireContext(), R.color.white))


        // Set the default tab to display as "SavedFragment"
        viewPager.currentItem = 0
    }
}