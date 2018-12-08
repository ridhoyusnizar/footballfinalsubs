package com.dicoding.ridho.kotlinfinalsubmission.favorites.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.db.databaseMatch
import com.dicoding.ridho.kotlinfinalsubmission.favorites.model.FavoriteMatch
import com.dicoding.ridho.kotlinfinalsubmission.match.detailMatch.MatchDetailActivity
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMatchFragment : Fragment() {
    private var favorites : MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var  adapter: FavoriteMatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(requireContext(),favorites){
            startActivity<MatchDetailActivity>("detail" to it.eventId)
        }
        rv_match.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rv_match.adapter=adapter
        showFavorite()
        swipeRefreshMatch.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }


    private fun showFavorite(){
        progress_bar.invisible()
        context?.databaseMatch?.use {
            swipeRefreshMatch.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}