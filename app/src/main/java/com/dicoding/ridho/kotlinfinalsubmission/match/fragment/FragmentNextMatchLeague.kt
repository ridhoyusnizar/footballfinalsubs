package com.dicoding.ridho.kotlinfinalsubmission.match.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import com.dicoding.ridho.kotlinfinalsubmission.R
import com.dicoding.ridho.kotlinfinalsubmission.api.ApiRepository
import com.dicoding.ridho.kotlinfinalsubmission.match.MatchAdapter
import com.dicoding.ridho.kotlinfinalsubmission.match.MatchPresenter
import com.dicoding.ridho.kotlinfinalsubmission.match.MatchView
import com.dicoding.ridho.kotlinfinalsubmission.match.detailMatch.MatchDetailActivity
import com.dicoding.ridho.kotlinfinalsubmission.model.Event
import com.dicoding.ridho.kotlinfinalsubmission.utils.Constant
import com.dicoding.ridho.kotlinfinalsubmission.utils.invisible
import com.dicoding.ridho.kotlinfinalsubmission.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.startActivity

class FragmentNextMatchLeague: Fragment(), MatchView, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener{
    private var listNextEvent: MutableList<Event> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var idLeague: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match,container,false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        setAdapter(listNextEvent)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext() ,android.R.layout.simple_spinner_dropdown_item,spinnerItems)
        spinnerMatch.adapter = spinnerAdapter
        spinnerMatch.onItemSelectedListener = this
        rv_match.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rv_match.adapter=adapter
        swipeRefreshMatch.setOnRefreshListener {
            presenter.getNextEvent(idLeague)
        }
    }
    override fun showLoading() {
        progress_bar.visible()
    }
    override fun hideLoading() {
        progress_bar.invisible()
    }
    override fun showEventList(showEventList: List<Event>) {
        swipeRefreshMatch.isRefreshing = false
        listNextEvent.clear()
        listNextEvent.addAll(showEventList)
        adapter.notifyDataSetChanged()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        idLeague = resources.getStringArray(R.array.id_league)[position]
        presenter.getNextEvent(idLeague)
    }

    private fun setAdapter(data: List<Event>) {
        adapter = MatchAdapter(requireContext(), data) {
            startActivity<MatchDetailActivity>(Constant.DETAIL to it.idEvent)
        }
        rv_match.adapter = adapter
    }

    override fun onQueryTextChange(search: String): Boolean {
        if (search.isNotEmpty()) {
            val cari = search.toLowerCase()
            val data = listNextEvent.filter {
                it.homeTeam.toLowerCase().contains(cari) ||
                        it.awayTeam.toLowerCase().contains(cari)
            }
            setAdapter(data)
        } else {
            setAdapter(listNextEvent)
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
            val editext: EditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)
            editext.hint = getString(R.string.search)
            searchView.setOnQueryTextListener(this)
        }

    }

}