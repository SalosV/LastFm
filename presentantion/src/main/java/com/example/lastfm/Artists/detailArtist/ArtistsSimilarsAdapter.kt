package com.example.lastfm.Artists.detailArtist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ArtistsSimilar
import com.example.lastfm.Artists.detailArtist.ArtistsSimilarsAdapter.ViewHolder
import com.example.lastfm.R
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class ArtistsSimilarsAdapter : RecyclerView.Adapter<ViewHolder>() {

    var artists: List<ArtistsSimilar> by Delegates.observable(
        emptyList(),
        { _, _, _ -> notifyDataSetChanged() }
    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val artistPicture by lazy { itemView.findViewById<ImageView>(R.id.artist_picture) }
        private val artistsName by lazy { itemView.findViewById<TextView>(R.id.artists_name) }

        fun bind(artistsSimilar: ArtistsSimilar) {

            artistsName.text = artistsSimilar.name

            if (artistsSimilar.url != "")
                Picasso.get().load(artistsSimilar.image[0].text).into(artistPicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = run {
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_artist_similar, parent, false)
        )
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artists[position])
    }
}