package com.example.visuallithuanian.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.visuallithuanian.ui.activities.fragments.HardFragment
import com.example.visuallithuanian.ui.activities.fragments.MediumFragment
import com.example.visuallithuanian.ui.activities.fragments.FlashCards

class TabLayoutAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle):
                    FragmentStateAdapter(fragmentManager,lifecycle){

    private val NUM_TABS = 3

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
       when(position){
           0-> FlashCards()
           1-> MediumFragment()
           2-> HardFragment()
       }
        return FlashCards()
    }


}