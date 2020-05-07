package com.example.lastfm.tracks.listTracks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Track
import com.example.lastfm.R
import com.example.lastfm.tracks.listTracks.TracksAdapter.ViewHolder
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class TracksAdapter(private val listener: (Track) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    var tracks: List<Track> by Delegates.observable(
        emptyList(),
        { _, _, _ -> notifyDataSetChanged() }
    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val trackPhoto by lazy { itemView.findViewById<ImageView>(R.id.track_photo) }
        private val trackName by lazy { itemView.findViewById<TextView>(R.id.track_name) }
        private val trackNameArtist by lazy { itemView.findViewById<TextView>(R.id.track_name_artist) }

        fun bind(track: Track, listener: (Track) -> Unit) {

            trackName.text = track.name
            trackNameArtist.text = track.artist.name

            if (track.image.isNotEmpty()) {
                if (track.image[0].text != "") {
                    Picasso.get().load(track.image[0].text).into(trackPhoto)
                }
            }

            itemView.setOnClickListener { listener(track) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = run {
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false))
    }

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tracks[position], listener)
    }
}