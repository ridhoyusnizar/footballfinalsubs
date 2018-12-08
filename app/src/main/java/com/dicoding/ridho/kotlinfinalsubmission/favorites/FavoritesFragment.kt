package com.dicoding.ridho.kotlinfinalsubmission.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import kotlinx.android.synthetic.main.fragment_btm_favorites.*

class FavoritesFragment : Fragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbarFavorites)
        val adapter = FavoritesFragmentAdapter(childFragmentManager)
        viewPagerFavorites.adapter = adapter
        tabLayoutFavorites.setupWithViewPager(viewPagerFavorites)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_btm_favorites, container, false)
    }
}