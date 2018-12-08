package com.dicoding.ridho.kotlinfinalsubmission.teams

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

class TeamsPresenterTest {
    @Mock
    private lateinit var view: TeamsView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson

    private lateinit var  presenter: TeamsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRepository, gson)
    }

    @Test
    fun getTeamList(){
        val listTeam: MutableList<Team> = mutableListOf()
        val response = TeamResponse(listTeam)

        val league = Constant.DETAIL
        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
                )
            ).thenReturn(response)
            presenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(listTeam)
            Mockito.verify(view).hideLoading()
        }
    }
    }
