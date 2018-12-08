package com.dicoding.ridho.kotlinfinalsubmission.api

import com.dicoding.ridho.kotlinfinalsubmission.BuildConfig

object TheSportDBApi {

    //https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328
    fun getNextEvent(league: String?): String {

        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + league
    }

    //https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
    fun getPastEvent(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + league

    }

    fun getDetailEvents(league:String?):String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + league
    }

    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l=" + league
    }
    //https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604
    fun  getDetailTeams(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + league
    }

    fun getPlayers(league: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php?id=" + league

    }

    fun getDetailPlayers(league: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupplayer.php?id=" + league
    }

}