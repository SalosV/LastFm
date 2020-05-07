package com.example.lastfm.tracks.detailTrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfm.Artists.detailArtist.TagsAdapter
import com.example.lastfm.R
import com.example.lastfm.tracks.detailTrack.DetailTrackState.Error
import com.example.lastfm.tracks.detailTrack.DetailTrackState.Loading
import com.example.lastfm.tracks.detailTrack.DetailTrackState.ShowInformationTrack
import com.example.lastfm.tracks.di.TracksViewModelComponent
import com.example.lastfm.tracks.di.TracksViewModelModule
import com.example.lastfm.utils.app
import com.example.lastfm.utils.fromHtml
import kotlinx.android.synthetic.main.activity_detail_trick.*
import kotlinx.android.synthetic.main.activity_detail_trick.progress
import kotlinx.android.synthetic.main.activity_detail_trick.toolbar

class DetailTrackActivity : AppCompatActivity() {

    private val viewModel by lazy { component.detailTracksViewModel }
    private val tagsAdapter by lazy { TagsAdapter() }
    private lateinit var component: TracksViewModelComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_trick)

        component = app.component.plusTracks(TracksViewModelModule())

        intent.extras?.apply {
            val artist = getString("artist", "")
            val track = getString("track", "")

            viewModel.getInfTrack(artist, track)
        }

        initToolbar()
        initObservable()
        initRecyclers()
    }

    private fun initRecyclers() {
        tagsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tagsList.adapter = tagsAdapter
    }

    private fun initObservable() {
        viewModel.detailTrackLiveData.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: DetailTrackState) {
        when(state) {
            is Loading -> {
                progress.visibility = VISIBLE
            }
            is ShowInformationTrack -> {

                state.track.apply {
                    nameTrack.text = name
                    biographyText.fromHtml(wiki.summary)
                    nameArtist.text = artist.name
                    published.text = wiki.published
                    noPlays.text = listeners
                    tagsAdapter.tags = topTags
                }

                progress.visibility = GONE
            }
            is Error -> {
                progress.visibility = GONE
                Toast.makeText(this, state.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

}
