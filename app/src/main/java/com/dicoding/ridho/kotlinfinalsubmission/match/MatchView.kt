package com.dicoding.ridho.kotlinfinalsubmission.match

import com.dicoding.ridho.kotlinfinalsubmission.model.Event

interface MatchView{
    fun hideLoading()
    fun showLoading()
    fun showEventList(showEventList:List<Event>)
}