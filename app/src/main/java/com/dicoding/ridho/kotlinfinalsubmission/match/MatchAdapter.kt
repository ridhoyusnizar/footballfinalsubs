package com.dicoding.ridho.kotlinfinalsubmission.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.model.Event
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.dicoding.ridho.kotlinfinalsubmission.utils.Time
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_match.*

class MatchAdapter(
    private val ctx: Context,
    private val league: List<Event>,
    private val listener: (Event) -> Unit
) : RecyclerView.Adapter<MatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchViewHolder {
        return MatchViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.list_match,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int =league.size

    override fun onBindViewHolder(p0: MatchViewHolder, p1: Int) {
        p0.bindItem(league[p1],listener)
    }

}

class MatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindItem(event: Event, listener: (Event) -> Unit) {
        val dateTime = Time.formatUTCtoGMT(event.dateEvent,event.time.substring(0,5), Constant.DATE_TIME)
        tvDateEvent.text = dateTime
        tvStrHomeTeam.text = event.homeTeam
        tvStrAwayTeam.text = event.awayTeam
        tvHomeScore.text = event.homeScore
        tvAwayScore.text = event.awayScore
        itemView.setOnClickListener {
            listener(event)
        }

    }
}