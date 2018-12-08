package com.dicoding.ridho.kotlinfinalsubmission.match.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.match.MatchesFragmentAdapter
import kotlinx.android.synthetic.main.fragment_btm_match.*

class MatchesFragment : Fragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbarMatch)
        val adapter = MatchesFragmentAdapter(childFragmentManager)
        viewPagerMatch.adapter = adapter
        tabLayoutMatch.setupWithViewPager(viewPagerMatch)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_btm_match, container, false)
    }
}