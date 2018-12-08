package com.dicoding.ridho.kotlinfinalsubmission.favorites.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.favorites.model.FavoriteMatch
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_match.*

class FavoriteMatchAdapter(
    private val ctx: Context,
    private val favorite: List<FavoriteMatch>,
    private val listener: (FavoriteMatch) -> Unit
) : RecyclerView.Adapter<FavoriteMatchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.list_match,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int =favorite.size

    override fun onBindViewHolder(p0: FavoriteMatchViewHolder, p1: Int) {
        p0.bindItem(favorite[p1],listener)
    }

}

class FavoriteMatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindItem(favorite: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
        tvDateEvent.text = favorite.dateEvent
        tvStrHomeTeam.text = favorite.homeTeam
        tvStrAwayTeam.text = favorite.awayTeam
        tvHomeScore.text = favorite.homeScore
        tvAwayScore.text = favorite.awayScore
        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}