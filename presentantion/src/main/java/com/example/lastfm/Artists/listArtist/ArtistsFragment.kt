package com.example.lastfm.Artists.listArtist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.lastfm.Artists.detailArtist.DetailArtistActivity
import com.example.lastfm.Artists.listArtist.ArtistsState.*
import com.example.lastfm.Artists.di.ArtistsViewModelComponent
import com.example.lastfm.Artists.di.ArtistsViewModelModule
import com.example.lastfm.MainActivity

import com.example.lastfm.R
import com.example.lastfm.utils.app
import com.example.lastfm.utils.progressHiddenDelay

class ArtistsFragment : Fragment() {

    private lateinit var component: ArtistsViewModelComponent
    private lateinit var mainActivity: MainActivity
    private val viewModel by lazy { component.artistsViewModel }
    private lateinit var artistsAdapter: ArtistsAdapter
    private lateinit var swipeListArtists: SwipeRefreshLayout

    private lateinit var listArtists: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artists, container, false)

        component = mainActivity
            .app.component
            .plus(ArtistsViewModelModule())

        initInstances(view)
        initRecycler()
        initObservers()

        return view
    }

    private fun initInstances(view: View) {
        listArtists = view.findViewById(R.id.list_artists)
        swipeListArtists = view.findViewById(R.id.swipe_list_artists)
    }

    private fun initRecycler() {

        listArtists.layoutManager = LinearLayoutManager(mainActivity)
        artistsAdapter = ArtistsAdapter {
            val intent = Intent(mainActivity, DetailArtistActivity::class.java).apply {
                putExtra("name", it.name)
                putExtra("mbid", it.mbid)
            }

            startActivity(intent)
        }

        listArtists.adapter = artistsAdapter

        swipeListArtists.setOnRefreshListener {
            viewModel.getArtists()
        }
    }

    private fun initObservers() {
        viewModel.artistsLiveData.observe(mainActivity, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: ArtistsState) {

        when (state) {
            is Loading -> {
                swipeListArtists.isRefreshing = true
            }
            is ShowArtists -> {
                artistsAdapter.artists = state.artists
                swipeListArtists.progressHiddenDelay()
            }
            is Error -> {
                Toast.makeText(mainActivity, state.msg, Toast.LENGTH_SHORT).show()
                swipeListArtists.progressHiddenDelay()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onResume() {
        super.onResume()
        viewModel.getArtists()
    }
}

