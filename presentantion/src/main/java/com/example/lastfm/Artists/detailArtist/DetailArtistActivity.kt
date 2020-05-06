package com.example.lastfm.Artists.detailArtist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastfm.Artists.detailArtist.DetailArtistState.*
import com.example.lastfm.Artists.di.ArtistsViewModelComponent
import com.example.lastfm.Artists.di.ArtistsViewModelModule
import com.example.lastfm.R
import com.example.lastfm.utils.app
import com.example.lastfm.utils.fromHtml
import com.example.lastfm.utils.makeLinks
import kotlinx.android.synthetic.main.activity_detail_artist.*

class DetailArtistActivity : AppCompatActivity() {

    private val viewModel by lazy { component.detailArtistViewModel }
    private val adapterArtistSimilar by lazy { ArtistsSimilarsAdapter() }
    private val tagsAdapter by lazy { TagsAdapter() }
    private lateinit var component: ArtistsViewModelComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_artist)

        component = app.component.plus(ArtistsViewModelModule())

        intent.extras?.apply {

            val name = getString("name", "")
            val mbid = getString("mbid", "")

            viewModel.getInfoArtist(name, mbid)
        }

        initToolbar()
        initObservable()
        initRecyclers()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun initRecyclers() {
        artistsSimilarsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        artistsSimilarsList.adapter = adapterArtistSimilar

        tagsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        tagsList.adapter = tagsAdapter
    }

    private fun initObservable() {

        viewModel.detailArtistLiveData.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: DetailArtistState) {
        when(state) {
            is ShowInformationArtist -> {
                state.artist.apply {
                    nameArtist.text = name
                    published.text = bio.published
                    biographyText.fromHtml(bio.summary)
                    page.text = url
                    adapterArtistSimilar.artists = similarArtists
                    tagsAdapter.tags = tags
                }
                progress.visibility = GONE
            }
            is Error -> {
                Toast.makeText(this, state.msg, Toast.LENGTH_SHORT).show()
                progress.visibility = GONE
            }
            is Loading -> {
                progress.visibility = VISIBLE
            }
        }
    }
}
