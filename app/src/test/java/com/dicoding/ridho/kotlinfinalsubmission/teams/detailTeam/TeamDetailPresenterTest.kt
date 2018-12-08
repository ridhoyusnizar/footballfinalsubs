package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.Team
import com.dicoding.ridho.kotlinfinalsubmission.model.TeamResponse
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamDetailPresenterTest {
    @Mock
    private lateinit var view: TeamDetailView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson

    private lateinit var  presenter: TeamDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view, apiRepository, gson)
    }
    @Test
    fun getTeamDetail() {
        val listTeamDetail: MutableList<Team> = mutableListOf()
        val response = TeamResponse(listTeamDetail)

        val league = Constant.DETAIL
        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getDetailTeams(league)),
                    TeamResponse::class.java
                )
            ).thenReturn(response)
            presenter.getTeamDetail(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailTeam(listTeamDetail)
            Mockito.verify(view).hideLoading()
        }
    }

}