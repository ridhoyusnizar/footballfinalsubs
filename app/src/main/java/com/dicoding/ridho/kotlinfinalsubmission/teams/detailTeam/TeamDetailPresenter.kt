package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(private val view: TeamDetailView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
) {

    fun getTeamDetail(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailTeams(league)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailTeam(data.teams)
            }
        }
    }
}