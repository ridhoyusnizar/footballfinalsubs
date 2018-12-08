package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player.detailPlayer

import com.dicoding.ridho.kotlinfinalsubmission.model.DetailPlayer

interface PlayerDetailView {
    fun hideLoading()
    fun showLoading()
   fun showPlayerDetail(data : List<DetailPlayer>)
}