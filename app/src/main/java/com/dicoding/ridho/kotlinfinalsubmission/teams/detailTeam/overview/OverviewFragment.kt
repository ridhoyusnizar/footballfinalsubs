package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment() {

    companion object {
        fun newInstance(id: String?): OverviewFragment {
            val bind = Bundle()
            bind.putString(Constant.DETAIL, id)
            val overViewFragment = OverviewFragment()
            overViewFragment.arguments = bind
            return overViewFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_overview,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bindData = arguments
         val description = bindData?.getString(Constant.DETAIL) ?: ""
        tvTeamDescription.text = description

    }

}