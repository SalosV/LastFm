package com.example.lastfm.tracks.listTracks

import android.content.Context
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
import com.example.lastfm.tracks.di.TracksViewModelComponent
import com.example.lastfm.tracks.di.TracksViewModelModule
import com.example.lastfm.tracks.listTracks.TracksState.Error
import com.example.lastfm.tracks.listTracks.TracksState.ShowTracks
import com.example.lastfm.utils.app
import com.example.lastfm.utils.progressHiddenDelay

class TracksFragment : Fragment() {

    private lateinit var component: TracksViewModelComponent
    private val viewModel by lazy { component.tracksViewModel }
    private lateinit var mainActivity: MainActivity
    private lateinit var tracksAdapter: TracksAdapter

    private lateinit var swipeListArtists: SwipeRefreshLayout
    private lateinit var listArtists: RecyclerView
    private lateinit var searchArtists: EditText
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
        initRecycler()
        initObservables()
        return view
    }

    private fun initObservables() {
        viewModel.tracksLiveData.observe(mainActivity, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: TracksState) {
        when(state) {
            is TracksState.Loading -> {
                swipeListArtists.isRefreshing = true
            }
            is ShowTracks -> {
                tracksAdapter.tracks = state.tracks
                swipeListArtists.progressHiddenDelay()
            }
            is Error -> {
                Toast.makeText(mainActivity, state.msg, Toast.LENGTH_SHORT).show()
                swipeListArtists.progressHiddenDelay()
            }
        }
    }

    private fun initInstances(view: View) {
        swipeListArtists = view.findViewById(R.id.swipe_list_artists)
        listArtists = view.findViewById(R.id.list_artists)
        toolbar = view.findViewById(R.id.toolbar)
        searchArtists = view.findViewById(R.id.search_artists)
    }

    private fun initRecycler() {

        listArtists.layoutManager = LinearLayoutManager(mainActivity)

        tracksAdapter = TracksAdapter {

        }

        listArtists.adapter = tracksAdapter

        swipeListArtists.setOnRefreshListener {
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
