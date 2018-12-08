package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.Player
import com.dicoding.ridho.kotlinfinalsubmission.model.PlayerResponse
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerPresenterTest {
    @Mock
    private lateinit var view: PlayerView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson

    private lateinit var  presenter: PlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson)
    }

    @Test
    fun getPlayer() {
        val listPlayer: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(listPlayer)

        val league = Constant.DETAIL
        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPlayers(league)),
                    PlayerResponse::class.java
                )
            ).thenReturn(response)
            presenter.getPlayer(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayerList(listPlayer)
            Mockito.verify(view).hideLoading()
        }
    }
}