package com.example.lastfm.Artists.listArtist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.lastfm.Artists.detailArtist.DetailArtistActivity
import com.example.lastfm.Artists.di.ArtistsViewModelComponent
import com.example.lastfm.Artists.di.ArtistsViewModelModule
import com.example.lastfm.Artists.listArtist.ArtistsState.*
import com.example.lastfm.MainActivity
import com.example.lastfm.R
import com.example.lastfm.utils.*
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ArtistsFragment : Fragment() {

    private lateinit var component: ArtistsViewModelComponent
    private lateinit var mainActivity: MainActivity
    private val viewModel by lazy { component.artistsViewModel }
    private lateinit var artistsAdapter: ArtistsAdapter
    private lateinit var swipeListArtists: SwipeRefreshLayout

    private lateinit var listArtists: RecyclerView
    private lateinit var searchArtists: EditText
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artists, container, false)

        component = mainActivity
            .app.component
            .plus(ArtistsViewModelModule())

        initInstances(view)
        initToolbar()
        initRecycler()
        initObservers()

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
                    searchArtists.visibility = VISIBLE
                    searchArtists.openKeyboard(mainActivity)
                    iconSelect.isVisible = false
                }
                R.id.icon_close -> {
                    toolbar.menu.findItem(R.id.icon_search).isVisible = true
                    iconSelect.isVisible = false
                    searchArtists.setText("")
                    searchArtists.hideKeyboard(mainActivity)
                    searchArtists.visibility = GONE
                }
            }
            true
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

        RxTextView.textChanges(searchArtists)
            .debounce(200, TimeUnit.MILLISECONDS)
            .map {
                if (it.isNotEmpty())
                    viewModel.searchArtists(it.toString())
                else
                    viewModel.getArtists()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
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

