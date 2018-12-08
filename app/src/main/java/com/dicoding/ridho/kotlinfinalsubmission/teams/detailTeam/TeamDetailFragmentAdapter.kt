package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.overview.OverviewFragment
import com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player.PlayerFragment

class TeamDetailFragmentAdapter(fragment :FragmentManager,private val id:String?) : FragmentPagerAdapter(fragment){
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "OVERVIEW"
            1 -> "PLAYERS"
            else -> ""
        }
    }

    override fun getCount(): Int = 2

    override fun getItem(p0: Int): Fragment? = when (p0) {
        0 -> OverviewFragment.newInstance(id)
        1 -> PlayerFragment()
        else -> null
    }
}