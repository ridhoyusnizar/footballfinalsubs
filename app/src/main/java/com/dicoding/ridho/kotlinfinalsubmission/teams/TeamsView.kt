package com.dicoding.ridho.kotlinfinalsubmission.teams

import com.dicoding.ridho.kotlinfinalsubmission.model.Team

interface TeamsView {
    fun hideLoading()
    fun showLoading()
    fun showTeamList(data: List<Team>)
}