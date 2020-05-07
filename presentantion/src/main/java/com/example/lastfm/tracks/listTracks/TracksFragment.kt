package com.example.lastfm.tracks.listTracks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.lastfm.MainActivity

import com.example.lastfm.R
import com.example.lastfm.tracks.detailTrack.DetailTrackActivity
import com.example.lastfm.tracks.di.TracksViewModelComponent
import com.example.lastfm.tracks.di.TracksViewModelModule
import com.example.lastfm.tracks.listTracks.TracksState.Error
import com.example.lastfm.tracks.listTracks.TracksState.ShowTracks
import com.example.lastfm.utils.app
import com.example.lastfm.utils.hideKeyboard
import com.example.lastfm.utils.openKeyboard
import com.example.lastfm.utils.progressHiddenDelay
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TracksFragment : Fragment() {

    private lateinit var component: TracksViewModelComponent
    private val viewModel by lazy { component.tracksViewModel }
    private lateinit var mainActivity: MainActivity
    private lateinit var tracksAdapter: TracksAdapter

    private lateinit var swipeListTracks: SwipeRefreshLayout
    private lateinit var listTracks: RecyclerView
    private lateinit var searchTracks: EditText
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tracks, container, false)

        component = mainActivity
            .app.component
            .plusTracks(TracksViewModelModule())

        initInstances(view)
        initToolbar()
        initRecycler()
        initObservables()
        return view
    }

    private fun initToolbar() {
        val menu = R.menu.menu_search
        toolbar.inflateMenu(menu)
        toolbar.setOnMenuItemClickListener { item ->
            val iconSelect = toolbar.menu.findItem(item.itemId)

            when (item.itemId) {
                R.id.icon_search -> {
                    toolbar.menu.findItem(R.id.icon_close).isVisible = true
                    searchTracks.visibility = View.VISIBLE
                    searchTracks.openKeyboard(mainActivity)
                    iconSelect.isVisible = false
                }
                R.id.icon_close -> {
                    toolbar.menu.findItem(R.id.icon_search).isVisible = true
                    iconSelect.isVisible = false
                    searchTracks.setText("")
                    searchTracks.hideKeyboard(mainActivity)
                    searchTracks.visibility = View.GONE
                }
            }
            true
        }
    }

    private fun initObservables() {
        viewModel.tracksLiveData.observe(mainActivity, Observer {
            renderState(it)
        })

        RxTextView.textChanges(searchTracks)
            .debounce(200, TimeUnit.MILLISECONDS)
            .map {
                if (it.isNotEmpty())
                    viewModel.searchTracks(it.toString())
                else
                    viewModel.getTracks()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun renderState(state: TracksState) {
        when(state) {
            is TracksState.Loading -> {
                swipeListTracks.isRefreshing = true
            }
            is ShowTracks -> {
                tracksAdapter.tracks = state.tracks
                swipeListTracks.progressHiddenDelay()
            }
            is Error -> {
                Toast.makeText(mainActivity, state.msg, Toast.LENGTH_SHORT).show()
                swipeListTracks.progressHiddenDelay()
            }
        }
    }

    private fun initInstances(view: View) {
        swipeListTracks = view.findViewById(R.id.swipe_list_tracks)
        listTracks = view.findViewById(R.id.list_tracks)
        toolbar = view.findViewById(R.id.toolbar)
        searchTracks = view.findViewById(R.id.search_tracks)
    }

    private fun initRecycler() {

        listTracks.layoutManager = LinearLayoutManager(mainActivity)

        tracksAdapter = TracksAdapter {

            val intent = Intent(mainActivity, DetailTrackActivity::class.java).apply {
                putExtra("artist", it.artist.name)
                putExtra("track", it.name)
            }

            startActivity(intent)
        }

        listTracks.adapter = tracksAdapter

        swipeListTracks.setOnRefreshListener {
            viewModel.getTracks()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTracks()
    }
}
