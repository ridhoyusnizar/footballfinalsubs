package com.dicoding.ridho.kotlinfinalsubmission.match.detailMatch

import com.dicoding.ridho.kotlinfinalsubmission.model.DetailEvent
import com.dicoding.ridho.kotlinfinalsubmission.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<DetailEvent>)
    fun showMatchImageDetail(dataHome:  List<Team>, dataAway: List<Team>)
}