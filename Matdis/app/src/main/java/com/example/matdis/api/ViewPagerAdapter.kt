package com.example.matdis.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.matdis.api.town.MyTownFragment

private val TABS_COUNT = 4

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> EarthFragment()
            1 -> MarsFragment()
            2 -> WeatherFragment()
            3-> MyTownFragment()
            else -> EarthFragment()
        }
    }

    override fun getCount(): Int {
        return TABS_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }
}