package com.example.lastfm.Artists.detailArtist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Tag
import com.example.lastfm.Artists.detailArtist.TagsAdapter.ViewHolder
import com.example.lastfm.R
import kotlin.properties.Delegates

class TagsAdapter: RecyclerView.Adapter<ViewHolder>() {

    var tags: List<Tag> by Delegates.observable(
        emptyList(),
        { _, _, _ -> notifyDataSetChanged() }
    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTag by lazy { itemView.findViewById<TextView>(R.id.name_tag) }

        fun bind(tag: Tag) {
            nameTag.text = tag.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = run {
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        )
    }

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position])
    }
}