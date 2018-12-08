package com.dicoding.ridho.kotlinfinalsubmission.match

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dicoding.ridho.kotlinfinalsubmission.match.fragment.FragmentLastMatchLeague
import com.dicoding.ridho.kotlinfinalsubmission.match.fragment.FragmentNextMatchLeague

class MatchesFragmentAdapter(fragment :FragmentManager) : FragmentPagerAdapter(fragment){
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "NEXT"
            1 -> "LAST"
            else -> ""
        }
    }

    override fun getCount(): Int = 2

    override fun getItem(p0: Int): Fragment? = when (p0) {
        0 -> FragmentNextMatchLeague()
        1 -> FragmentLastMatchLeague()
        else -> null
    }
}