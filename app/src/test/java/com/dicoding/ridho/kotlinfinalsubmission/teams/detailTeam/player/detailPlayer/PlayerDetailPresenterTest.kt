package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player.detailPlayer

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailPlayer
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailPlayerResponse
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerDetailPresenterTest {
    @Mock
    private lateinit var view: PlayerDetailView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson

    private lateinit var  presenter: PlayerDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailPresenter(view, apiRepository, gson)
    }

    @Test
    fun getDetailPlayer() {
        val listDetailPlayer: MutableList<DetailPlayer> = mutableListOf()
        val response = DetailPlayerResponse(listDetailPlayer)

        val league = Constant.DETAIL
        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getDetailPlayers(league)),
                    DetailPlayerResponse::class.java
                )
            ).thenReturn(response)
            presenter.getDetailPlayer(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayerDetail(listDetailPlayer)
            Mockito.verify(view).hideLoading()
        }
    }
}