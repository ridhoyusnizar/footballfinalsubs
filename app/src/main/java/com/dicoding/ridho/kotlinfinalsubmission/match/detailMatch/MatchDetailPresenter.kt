package com.dicoding.ridho.kotlinfinalsubmission.match.detailMatch

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailEventResponse
import com.dicoding.ridho.kotlinfinalsubmission.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MatchDetailPresenter(private val view:MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson
){

    fun getDetail(league: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailEvents(league)),
                DetailEventResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.events)
            }
        }
    }

    fun getBadgeTeam(dataHome:String?,dataAway: String?){
        view.showLoading()
        doAsync {
            val dataH = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailTeams(dataHome)),
                TeamResponse::class.java)
            val dataA = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailTeams(dataAway)),
                TeamResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showMatchImageDetail(dataH.teams,dataA.teams)
            }
        }
    }
}