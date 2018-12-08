package com.dicoding.ridho.kotlinfinalsubmission.match

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.EventResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view:MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
){
    fun getPastEvent(league: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPastEvent(league)),
                EventResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showEventList(data.events)

            }
        }
    }

    fun getNextEvent(league:String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getNextEvent(league)),
                EventResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showEventList(data.events)

            }
        }
    }
}