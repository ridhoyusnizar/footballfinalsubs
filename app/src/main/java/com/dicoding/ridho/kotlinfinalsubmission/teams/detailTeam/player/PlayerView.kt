package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player

import com.dicoding.ridho.kotlinfinalsubmission.model.Player

interface PlayerView {
    fun hideLoading()
    fun showLoading()
    fun showPlayerList(data: List<Player>)
}