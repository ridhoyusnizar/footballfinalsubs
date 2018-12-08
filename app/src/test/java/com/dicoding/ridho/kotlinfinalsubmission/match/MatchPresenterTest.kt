package com.dicoding.ridho.kotlinfinalsubmission.match

import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.api.TheSportDBApi
import com.dicoding.ridho.kotlinfinalsubmission.model.Event
import com.dicoding.ridho.kotlinfinalsubmission.model.EventResponse
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private lateinit var view: MatchView
    @Mock
    private lateinit var apiRepository: ApiRepository
    @Mock
    private lateinit var gson: Gson

    private lateinit var  presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson)
    }

    @Test
    fun getPastEvent() {
        val event: MutableList<Event> = mutableListOf()
        val response = EventResponse(event)

        val league = Constant.DETAIL
        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPastEvent(league)),
                    EventResponse::class.java
                )
            ).thenReturn(response)
            presenter.getPastEvent(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(event)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getNextEvent() {
        val event: MutableList<Event> = mutableListOf()
        val response = EventResponse(event)

        val league = Constant.DETAIL
        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getNextEvent(league)),
                    EventResponse::class.java
                )
            ).thenReturn(response)
            presenter.getNextEvent(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(event)
            Mockito.verify(view).hideLoading()
        }
    }
}