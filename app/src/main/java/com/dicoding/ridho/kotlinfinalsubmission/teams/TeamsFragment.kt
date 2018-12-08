package com.dicoding.ridho.kotlinfinalsubmission.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.R.array.league
import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.model.Team
import com.dicoding.ridho.kotlinfinalsubmission.teams.detailTeam.TeamDetailActivity
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import com.dicoding.ridho.kotlinfinalsubmission.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_btm_teams.*
import org.jetbrains.anko.support.v4.startActivity

class TeamsFragment: Fragment(), TeamsView, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener{
    private var listTeam: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var leagueName: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_btm_teams,container,false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbarTeam)
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,spinnerItems)
        spinnerTeam.adapter = spinnerAdapter
        setAdapter(listTeam)
        spinnerTeam.onItemSelectedListener = this

        swipeRefreshTeam.setOnRefreshListener {
            presenter.getTeamList(leagueName)
            hideLoading()
        }
    }
    override fun showLoading() {
        progress_bar_team.visible()
    }
    override fun hideLoading() {
        progress_bar_team.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefreshTeam.isRefreshing = false
        listTeam.clear()
        listTeam.addAll(data)
        adapter.notifyDataSetChanged()
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        leagueName = spinnerTeam.selectedItem.toString()
        val data = leagueName.split(' ')
        leagueName = data.joinToString("%20")
        presenter.getTeamList(leagueName)
    }

    private fun setAdapter(data: List<Team>) {
        adapter = TeamsAdapter(requireContext(), data) {
            startActivity<TeamDetailActivity>(Constant.DETAIL to it.teamId)
        }
        rv_team.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rv_team.adapter=adapter
    }

    override fun onQueryTextChange(search: String): Boolean {
        if (search.isNotEmpty()) {
            val cari = search.toLowerCase()
            val data = listTeam.filter {
                it.teamName.toLowerCase().contains(cari)
            }
            setAdapter(data)
        } else {
            setAdapter(listTeam)
        }
        return true
    }

    override fun onQueryTextSubmit(search: String?): Boolean {
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val edtText: EditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)
            edtText.hint = getString(R.string.search)
            searchView.setOnQueryTextListener(this)
        }

    }

}