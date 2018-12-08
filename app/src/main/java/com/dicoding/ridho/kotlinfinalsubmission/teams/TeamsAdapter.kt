package com.dicoding.ridho.kotlinfinalsubmission.teams

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_teams.*

class TeamsAdapter(
    private val ctx: Context,
    private val league: List<Team>,
    private val listener: (Team) -> Unit
) : RecyclerView.Adapter<TeamsViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamsViewHolder {
        return TeamsViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.list_teams,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int =league.size

    override fun onBindViewHolder(p0: TeamsViewHolder, p1: Int) {
        p0.bindItem(league[p1],listener)
    }

}

class TeamsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindItem(team: Team, listener: (Team) -> Unit) {
        Picasso.get().load(team.teamBadge).into(imgTeam)
        tvTeamName.text = team.teamName
        itemView.setOnClickListener {
            listener(team)
        }
    }
}