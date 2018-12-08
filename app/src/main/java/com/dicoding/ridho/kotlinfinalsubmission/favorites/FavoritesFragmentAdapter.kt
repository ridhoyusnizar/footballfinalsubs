package com.dicoding.ridho.kotlinfinalsubmission.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.ridho.kotlinfinalsubmission.favorites.match.FavoriteMatchFragment
import com.dicoding.ridho.kotlinfinalsubmission.favorites.teams.FavoritesTeamFragment

class FavoritesFragmentAdapter(fragment : FragmentManager) : FragmentPagerAdapter(fragment){
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "MATCH"
            1 -> "TEAM"
            else -> ""
        }
    }

    override fun getCount(): Int = 2

    override fun getItem(p0: Int): Fragment? = when (p0) {
        0 -> FavoriteMatchFragment()
        1 -> FavoritesTeamFragment()
        else -> null
    }
}