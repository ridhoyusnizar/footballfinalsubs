package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_players.*

class PlayerAdapter(
    private val ctx: Context,
    private val league: List<Player>,
    private val listener: (Player) -> Unit
) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.list_players,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int =league.size

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bindItem(league[p1],listener)
    }

}

class PlayerViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindItem(player: Player, listener: (Player) -> Unit) {
        Picasso.get().load(player.strCutout).into(imgPlayer)
        tvPlayerName.text = player.strPlayer
        tvPlayerPosition.text = player.strPosition

        itemView.setOnClickListener {
            listener(player)
        }
    }
}