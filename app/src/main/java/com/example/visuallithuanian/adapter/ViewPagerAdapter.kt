package com.example.visuallithuanian.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.visuallithuanian.ui.activities.fragments.CardsFragment
import com.example.visuallithuanian.ui.activities.fragments.SavedFragment

class ViewPagerAdapter(fragment:Fragment):FragmentStateAdapter(fragment) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    fun addFragment(fragment: Fragment,title: String){
        fragments.add(fragment)
        titles.add(title)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SavedFragment()
            1 -> CardsFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }

    fun getPageTitle(position: Int):CharSequence?{
        return titles[position]
    }
}