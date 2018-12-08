package com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.db.databaseTeam
import com.dicoding.ridho.kotlinfinalsubmission.favorites.model.FavoriteTeam
import com.dicoding.ridho.kotlinfinalsubmission.model.Team
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import com.dicoding.ridho.kotlinfinalsubmission.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_teams.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var eventId: String
    private var listTeamDetail:MutableList<Team> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private fun removeFromFavorite(){
        try {
            databaseTeam.use {
                delete(
                    FavoriteTeam.TABLE_FAVORITE, "(${FavoriteTeam.ID_TEAM} = {${FavoriteTeam.ID_DELETE}})",
                    "${FavoriteTeam.ID_DELETE}" to eventId)
            }
            swipeRefreshDetailTeam.snackbar("Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefreshDetailTeam.snackbar(e.localizedMessage).show()
        }
    }
//    private fun verifyAvailableNetwork(activity: MatchDetailActivity):Boolean{
//        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.activeNetworkInfo
//        return networkInfo!= null && networkInfo.isConnected
//    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState(){
        databaseTeam.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
                .whereArgs("(${FavoriteTeam.ID_TEAM} = {id})",
                    "id" to eventId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
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
            R.id.add_to_favorite -> {
                if(listTeamDetail.isNotEmpty()){
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
            databaseTeam.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE,
                    FavoriteTeam.ID_TEAM to eventId,
                    FavoriteTeam.TEAM_NAME to listTeamDetail[0].teamName,
                    FavoriteTeam.TEAM_BADGE to listTeamDetail[0].teamBadge
                )
            }
            swipeRefreshDetailTeam.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            swipeRefreshDetailTeam.snackbar(e.localizedMessage).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_detail_teams)
        val intent = intent
        eventId = intent.getStringExtra(Constant.DETAIL)
        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = TeamDetailPresenter(this,request,gson)
        presenter.getTeamDetail(eventId)
        swipeRefreshDetailTeam.setOnRefreshListener {
            presenter.getTeamDetail(eventId)
        }
    }

    override fun showLoading() {
        progress_bar_detail_team.visible()
    }
    override fun hideLoading() {
        progress_bar_detail_team.invisible()
    }

    override fun showDetailTeam(data: List<Team>) {
        listTeamDetail.clear()
        listTeamDetail.addAll(data)
        val adapter = TeamDetailFragmentAdapter(supportFragmentManager, data[0].teamDescription)
        viewpagerTeam.adapter = adapter
        tabTeam.setupWithViewPager(viewpagerTeam)
        Picasso.get().load(data[0].teamBadge).into(img_team_badge)
        tvNameTeam.text = data[0].teamName
        tvDateTeamDetail.text = data[0].teamFormedYear
        tvStadiumTeam.text = data[0].teamStadium
    }
}