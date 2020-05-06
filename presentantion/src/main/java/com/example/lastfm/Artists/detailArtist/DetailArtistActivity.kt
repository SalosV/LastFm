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
import com.example.lastfm.Artists.detailArtist.DetailArtistState.*
import com.example.lastfm.Artists.di.ArtistsViewModelComponent
import com.example.lastfm.Artists.di.ArtistsViewModelModule
import com.example.lastfm.R
import com.example.lastfm.utils.app
import kotlinx.android.synthetic.main.activity_detail_artist.*

class DetailArtistActivity : AppCompatActivity() {

    private lateinit var component: ArtistsViewModelComponent
    private val viewModel by lazy { component.detailArtistViewModel }

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
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
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
                    published.text = "Publicación: ${bio.published}"
                    contentText.text = bio.content
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
