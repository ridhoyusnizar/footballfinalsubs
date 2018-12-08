package com.dicoding.ridho.kotlinfinalsubmission.model

import com.google.gson.annotations.SerializedName


data class Event(
//    idEvent
    @SerializedName("idEvent")
    val idEvent: String? = null,
//    dateEvent
    @SerializedName("dateEvent")
    val dateEvent: String,
    //time
    @SerializedName("strTime")
    val time: String,
//    strEvent
    @SerializedName("strEvent")
    var strEvent: String? = null,
//    idHomeTeam
    @SerializedName("idHomeTeam")
    val idHomeTeam: String? = null,
//    strHomeTeam
    @SerializedName("strHomeTeam")
    val homeTeam: String,
//    intHomeScore
    @SerializedName("intHomeScore")
    val homeScore: String? = null,
//    strHomeGoalDetails
    @SerializedName("strHomeGoalDetails")
    val homeGoalDetails: String? = null,
//    intHomeShots
    @SerializedName("intHomeShots")
    val homeShots: String? = null,
//    strHomeLineupGoalkeeper
    @SerializedName("strHomeLineupGoalkeeper")
    val homeLineupGoalkeeper: String? = null,
//    strHomeLineupDefense
    @SerializedName("strHomeLineupDefense")
    val homeLineupDefense: String? = null,
//    strHomeLineupMidfield
    @SerializedName("strHomeLineupMidfield")
    val homeLineupMidfield: String? = null,
//    strHomeLineupForward
    @SerializedName("strHomeLineupForward")
    val homeLineupForward: String? = null,
//    strHomeLineupSubstitutes
    @SerializedName("strHomeLineupSubstitutes")
    val homeLineupSubstitutes: String? = null,
//    strHomeFormation
    @SerializedName("strHomeFormation")
    val homeFormation: String? = null,
//
//    idAwayTeam
    @SerializedName("idAwayTeam")
    val idAwayTeam: String? = null,
//    strAwayTeam
    @SerializedName("strAwayTeam")
    val awayTeam: String,
//    intAwayScore
    @SerializedName("intAwayScore")
    val awayScore: String? = null,
//    strAwayGoalDetails
    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails: String? = null,
//    intAwayShots
    @SerializedName("intAwayShots")
    val awayShots: String? = null,
//    strAwayLineupGoalkeeper
    @SerializedName("strAwayLineupGoalkeeper")
    val awayLineupGoalkeeper: String? = null,
//    strAwayLineupDefense
    @SerializedName("strAwayLineupDefense")
    val awayLineupDefense: String? = null,
//    strAwayLineupMidfield
    @SerializedName("strAwayLineupMidfield")
    val awayLineupMidfield: String? = null,
//    strAwayLineupForward
    @SerializedName("strAwayLineupForward")
    val awayLineupForward: String? = null,
//    strAwayLineupSubstitutes
    @SerializedName(" strAwayLineupSubstitutes")
    val  awayLineupSubstitutes: String? = null,
//    strAwayFormation
    @SerializedName("strAwayFormation")
    val awayFormation: String? = null
)