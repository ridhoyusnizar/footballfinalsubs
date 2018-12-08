package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player.detailPlayer

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailPlayerResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayerDetailPresenter(private val view: PlayerDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
) {

    fun getDetailPlayer(league: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getDetailPlayers(league)),
                DetailPlayerResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showPlayerDetail(data.players)
            }
        }
    }

}