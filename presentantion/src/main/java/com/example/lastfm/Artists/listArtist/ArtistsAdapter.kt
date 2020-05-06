package com.example.lastfm.Artists.listArtist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Artists
import com.example.lastfm.Artists.listArtist.ArtistsAdapter.ViewHolder
import com.example.lastfm.R
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class ArtistsAdapter(private val listener: (Artists) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    var artists: List<Artists> by Delegates.observable(
        arrayListOf(),
        { _, _, _ -> notifyDataSetChanged() }
    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistsPhoto by lazy { itemView.findViewById<ImageView>(R.id.artists_photo) }
        private val artistsName by lazy { itemView.findViewById<TextView>(R.id.artists_name) }

        fun bind(artist: Artists, listener: (Artists) -> Unit) {

            Picasso.get().load(artist.image[0].text).into(artistsPhoto)
            artistsName.text = artist.name

            itemView.setOnClickListener { listener(artist) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = run {
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_artists, parent, false)
        )
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artists[position], listener)
    }
}