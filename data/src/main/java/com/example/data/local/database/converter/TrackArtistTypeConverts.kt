package com.example.data.local.database.converter

import androidx.room.TypeConverter
import com.example.data.remote.models.TrackArtist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TrackArtistTypeConverts {

    private val gson = Gson()

    @TypeConverter
    fun stringToTrack(data: String?): TrackArtist {
        if (data == null) {
            return TrackArtist()
        }
        val type = object : TypeToken<TrackArtist>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun trackToString(trackArtist: TrackArtist): String {
        if (trackArtist != null) {
            gson.toJson(trackArtist)
        }
        return gson.toJson(trackArtist)
    }
}