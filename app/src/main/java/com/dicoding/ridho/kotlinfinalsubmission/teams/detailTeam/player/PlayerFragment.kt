package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.model.Player
import com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player.detailPlayer.PlayerDetailActivity
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import com.dicoding.ridho.kotlinfinalsubmission.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_btm_teams.*
import kotlinx.android.synthetic.main.fragment_match.*
import kotlinx.android.synthetic.main.fragment_player.*
import org.jetbrains.anko.support.v4.startActivity

class PlayerFragment: Fragment(), PlayerView {
    private var listPlayer: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter
    private var idLeague: String? = null


    override fun showPlayerList(data: List<Player>) {
        swipeRefreshPlayer.isRefreshing = false
        listPlayer.clear()
        listPlayer.addAll(data)
        Log.d("_DEBUG",data.toString())
        adapter.notifyDataSetChanged()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_player,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        val intent = activity?.intent
        idLeague = intent?.getStringExtra(Constant.DETAIL)
        presenter.getPlayer(idLeague)
        adapter = PlayerAdapter(requireContext(), listPlayer) {
            startActivity<PlayerDetailActivity>(Constant.DETAIL to it.idPlayer)
        }
        rv_player.layoutManager= LinearLayoutManager(context as Context, LinearLayoutManager.VERTICAL,false)
        rv_player.adapter=adapter
        swipeRefreshPlayer.setOnRefreshListener {
            presenter.getPlayer(idLeague)
            hideLoading()
        }
    }
    override fun showLoading() {
        progress_bar_player.visible()
    }
    override fun hideLoading() {
        progress_bar_player.invisible()
    }



}