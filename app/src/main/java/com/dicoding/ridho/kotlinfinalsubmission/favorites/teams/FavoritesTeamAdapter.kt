package com.dicoding.ridho.kotlinfinalsubmission.favorites.teams

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.favorites.model.FavoriteTeam
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_teams.*

class FavoritesTeamAdapter(
    private val ctx: Context,
    private val favorite: List<FavoriteTeam>,
    private val listener: (FavoriteTeam) -> Unit
) : RecyclerView.Adapter<FavoriteTeamViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(
            LayoutInflater.from(ctx).inflate(
                R.layout.list_teams,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int =favorite.size

    override fun onBindViewHolder(p0: FavoriteTeamViewHolder, p1: Int) {
        p0.bindItem(favorite[p1],listener)
    }

}

class FavoriteTeamViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bindItem(favorite: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Picasso.get().load(favorite.teamBadge).into(imgTeam)
        tvTeamName.text = favorite.teamName
        itemView.setOnClickListener {
            listener(favorite)
        }
    }
}