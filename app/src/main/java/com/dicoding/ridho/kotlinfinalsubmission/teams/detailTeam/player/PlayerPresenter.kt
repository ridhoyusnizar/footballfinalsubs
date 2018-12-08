package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player

import android.util.Log
import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.PlayerResponse
import com.dicoding.ridho.kotlinfinalsubmission.model.TeamResponse
import com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.TeamDetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayerPresenter(private val view: PlayerView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson
) {

    fun getPlayer(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPlayers(league)),
                PlayerResponse::class.java
            )
            Log.d("__DEBUG",data.toString())
            uiThread {
                view.hideLoading()
                view.showPlayerList(data.player)
            }
        }
    }
}
