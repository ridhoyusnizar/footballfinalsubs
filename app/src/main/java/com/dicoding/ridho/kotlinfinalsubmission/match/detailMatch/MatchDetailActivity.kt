package com.dicoding.ridho.kotlinfinalsubmission.match.detailMatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.R.drawable.ic_add_to_favorites
import com.dicoding.ridho.kotlinfinalsubmission.R.drawable.ic_added_to_favorites
import com.dicoding.ridho.kotlinfinalsubmission.R.id.add_to_favorite
import com.dicoding.ridho.kotlinfinalsubmission.R.menu.detail_menu
import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.db.databaseMatch
import com.dicoding.ridho.kotlinfinalsubmission.favorites.model.FavoriteMatch
import com.dicoding.ridho.kotlinfinalsubmission.model.DetailEvent
import com.dicoding.ridho.kotlinfinalsubmission.model.Team
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.dicoding.ridho.kotlinfinalsubmission.utils.Time
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import com.dicoding.ridho.kotlinfinalsubmission.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find

class MatchDetailActivity : AppCompatActivity(),MatchDetailView{
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var eventId: String
    private lateinit var imageHomeTeam : ImageView
    private lateinit var imageAwayTeam : ImageView
    private var eventList:MutableList<DetailEvent> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private fun removeFromFavorite(){
        try {
            databaseMatch.use {
                delete(FavoriteMatch.TABLE_FAVORITE, "(${FavoriteMatch.ID_EVENT} = {${FavoriteMatch.ID_DELETE}})",
                    "${FavoriteMatch.ID_DELETE}" to eventId)
            }
            swipeRefreshDetail.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefreshDetail.snackbar(e.localizedMessage).show()
        }
    }
//    private fun verifyAvailableNetwork(activity: MatchDetailActivity):Boolean{
//        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo!= null && networkInfo.isConnected
//    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState(){
        databaseMatch.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                .whereArgs("(${FavoriteMatch.ID_EVENT} = {id})",
                    "id" to eventId)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if(eventList.isNotEmpty()){
                    if (isFavorite) removeFromFavorite() else addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                }else {
                    Toast.makeText(this,"Data belum ada", Toast.LENGTH_SHORT).show()
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            databaseMatch.use {
                insert(FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.ID_EVENT to eventId,
                    FavoriteMatch.DATE_EVENT to eventList[0].dateEvent,
                    FavoriteMatch.HOME_TEAM to eventList[0].homeTeam,
                    FavoriteMatch.AWAY_TEAM to eventList[0].awayTeam,
                    FavoriteMatch.HOME_SCORE to eventList[0].homeScore,
                    FavoriteMatch.AWAY_SCORE to eventList[0].awayScore
                )
            }
            swipeRefreshDetail.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefreshDetail.snackbar(e.localizedMessage).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_detail_match)
        val intent = intent
        eventId = intent.getStringExtra(Constant.DETAIL)
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = MatchDetailPresenter(this,request,gson)
        presenter.getDetail(eventId)
        presenter.getBadgeTeam("133602","133614")
        swipeRefreshDetail.setOnRefreshListener {
            presenter.getDetail(eventId)
        }

    }

    override fun showLoading() {
        progress_bar_detail.visible()
    }
    override fun hideLoading() {
        progress_bar_detail.invisible()
    }


    override fun showMatchDetail(data: List<DetailEvent>) {

        eventList.clear()
        eventList.addAll(data)

        val dateTime = Time.formatUTCtoGMT(data[0].dateEvent,data[0].time.substring(0, 5),Constant.DATE_TIME)
        tvDateEvent.text = dateTime
        tvStrHomeTeam.text = data[0].homeTeam
        tvStrAwayTeam.text = data[0].awayTeam

        tvHomeScore.text = data[0].homeScore
        tvAwayScore.text = data[0].awayScore

        tvGoalHomeDetails.text = data[0].homeGoalDetails
        tvGoalAwayDetails.text = data[0].awayGoalDetails

        tvHomeShot.text = data[0].homeShots
        tvAwayShot.text = data[0].awayShots

        tvHomeGoalKeeper.text = data[0].homeLineupGoalkeeper
        tvAwayGoalKeeper.text = data[0].awayLineupGoalkeeper

        tvHomeDefense.text = data[0].homeLineupDefense
        tvAwayDefense.text = data[0].awayLineupDefense

        tvHomeMidfield.text = data[0].homeLineupMidfield
        tvAwayMidfield.text = data[0].awayLineupMidfield

        tvHomeForward.text = data[0].homeLineupForward
        tvAwayForward.text = data[0].awayLineupForward

        tvHomeSubstitutes.text = data[0].homeLineupSubstitutes
        tvAwaySubstitutes.text = data[0].awayLineupSubstitutes

        presenter.getBadgeTeam(data[0].idHomeTeam.toString(),data[0].idAwayTeam.toString())
    }

    override fun showMatchImageDetail(dataHome: List<Team>, dataAway: List<Team>) {
        imageHomeTeam = find(R.id.imgHomeTeam)
        imageAwayTeam = find(R.id.imgAwayTeam)
        Picasso.get().load(dataHome[0].teamBadge).into(imageHomeTeam)
        Picasso.get().load(dataAway[0].teamBadge).into(imageAwayTeam)
    }
}