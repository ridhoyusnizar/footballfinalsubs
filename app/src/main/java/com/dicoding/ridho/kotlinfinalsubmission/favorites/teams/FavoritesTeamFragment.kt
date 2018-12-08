package com.dicoding.ridho.kotlinfinalsubmission.favorites.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.db.databaseTeam
import com.dicoding.ridho.kotlinfinalsubmission.favorites.model.FavoriteTeam
import com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.TeamDetailActivity
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoritesTeamFragment : Fragment() {
    private var favorites : MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var  adapter: FavoritesTeamAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoritesTeamAdapter(requireContext(),favorites){
            startActivity<TeamDetailActivity>("detail" to it.teamId)
        }
        rv_team.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rv_team.adapter=adapter
        showFavorite()
        swipeRefreshTeam.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }


    private fun showFavorite(){
        progress_bar_team.invisible()
        context?.databaseTeam?.use {
            swipeRefreshTeam.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}