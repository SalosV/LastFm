package com.example.lastfm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lastfm.Artists.listArtist.ArtistsFragment
import com.example.lastfm.tracks.TracksFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationBottomItemSelect()

        navView.selectedItemId = R.id.navigation_artists
    }

    private fun navigationBottomItemSelect() {
        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_artists -> {
                    replace(ArtistsFragment())
                }
                R.id.navigation_tracks -> {
                    replace(TracksFragment())
                }
            }
            true
        }
    }

    private fun replace(fragmentClass: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                android.R.anim.fade_out
            )
            .replace(R.id.frame_layout, fragmentClass).commit()
    }
}
