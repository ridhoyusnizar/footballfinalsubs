package com.dicoding.ridho.kotlinfinalsubmission.api

import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import org.junit.Test
import org.mockito.Mockito

class ApiRepositoryTest {
    @Test
    fun testNextEventDoRequest(){
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = TheSportDBApi.getNextEvent(Constant.DETAIL)
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }
    @Test
    fun testPastEventDoRequest(){
        val apiRepository = Mockito.mock(ApiRepository::class.java)
        val url = TheSportDBApi.getPastEvent(Constant.DETAIL)
        apiRepository.doRequest(url)
        Mockito.verify(apiRepository).doRequest(url)
    }

    @Test
fun testDetailEventsDoRequest(){
    val apiRepository = Mockito.mock(ApiRepository::class.java)
    val url = TheSportDBApi.getDetailEvents(Constant.DETAIL)
    apiRepository.doRequest(url)
    Mockito.verify(apiRepository).doRequest(url)
}

@Test
fun testTeamsDoRequest(){
    val apiRepository = Mockito.mock(ApiRepository::class.java)
    val url = TheSportDBApi.getTeams(Constant.DETAIL)
    apiRepository.doRequest(url)
    Mockito.verify(apiRepository).doRequest(url)
}

@Test
fun testDetailTeamsDoRequest(){
    val apiRepository = Mockito.mock(ApiRepository::class.java)
    val url = TheSportDBApi.getDetailTeams(Constant.DETAIL)
    apiRepository.doRequest(url)
    Mockito.verify(apiRepository).doRequest(url)
}

@Test
fun testPlayersDoRequest(){
    val apiRepository = Mockito.mock(ApiRepository::class.java)
    val url = TheSportDBApi.getPlayers(Constant.DETAIL)
    apiRepository.doRequest(url)
    Mockito.verify(apiRepository).doRequest(url)
}

@Test
fun testDetailPlayersDoRequest(){
    val apiRepository = Mockito.mock(ApiRepository::class.java)
    val url = TheSportDBApi.getDetailPlayers(Constant.DETAIL)
    apiRepository.doRequest(url)
    Mockito.verify(apiRepository).doRequest(url)
}

}