package com.dicoding.ridho.kotlinfinalsubmission.match.detailMatch

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailEvent
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailEventResponse
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

class MatchDetailPresenterTest {
    @Mock
    private lateinit var view: MatchDetailView
    @Mock
    private lateinit var gson: Gson
    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson)
    }
    @Test
    fun getDetail() {
        val eventList: MutableList<DetailEvent> = mutableListOf()
        val response = DetailEventResponse(eventList)
        val league = Constant.DETAIL

        GlobalScope.launch {
                        Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getDetailEvents(league)),
                    DetailEventResponse::class.java
                )
            ).thenReturn(response)
            presenter.getDetail(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchDetail(eventList)
            Mockito.verify(view).hideLoading()
        }
    }
    @Test
    fun getBadgeTeam() {
        val teamList: MutableList<Team> = mutableListOf()
        val response =TeamResponse(teamList)
        val league = Constant.DETAIL

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getDetailTeams(league)),
                    TeamResponse::class.java
                )
            ).thenReturn(response)
            presenter.getBadgeTeam(league,league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchImageDetail(teamList,teamList)
            Mockito.verify(view).hideLoading()
        }
    }

}