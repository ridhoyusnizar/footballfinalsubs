package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam

import com.dicoding.ridho.kotlinfinalsubmission.model.Team

interface TeamDetailView {
    fun hideLoading()
    fun showLoading()
    fun showDetailTeam(data: List<Team>)
}