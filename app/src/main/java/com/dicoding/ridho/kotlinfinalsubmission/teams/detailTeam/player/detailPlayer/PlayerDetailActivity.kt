package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player.detailPlayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailPlayer
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import com.dicoding.ridho.kotlinfinalsubmission.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.activity_detail_player.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {
    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var playerId: String
    private var listDetailPlayer:MutableList<DetailPlayer> = mutableListOf()

    override fun showPlayerDetail(data: List<DetailPlayer>) {
        listDetailPlayer.clear()
        listDetailPlayer.addAll(data)
        Picasso.get().load(data[0].strFanart1).into(imgPlayerDetail)
        tvWeight.text = data[0].strWeight
        tvHeight.text = data[0].strHeight
        tvPosition.text = data[0].strPosition
        tvPlayerDescription.text = data[0].strDescriptionEN
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_detail_player)
        supportActionBar?.title = "Player Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        playerId = intent.getStringExtra(Constant.DETAIL)
        val request = ApiRepository()
        val gson = Gson()

        presenter = PlayerDetailPresenter(this,request,gson)
        presenter.getDetailPlayer(playerId)

    }

    override fun showLoading() {
        progress_bar_detail_player.visible()
    }
    override fun hideLoading() {
        progress_bar_detail_player.invisible()
    }


}