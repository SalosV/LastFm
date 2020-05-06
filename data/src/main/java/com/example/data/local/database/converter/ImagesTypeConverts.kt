package com.example.data.local.database.converter

import androidx.room.TypeConverter
import com.example.data.remote.models.ImageResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImagesTypeConverts {

    private val gson = Gson()

    @TypeConverter
    fun stringToImages(data: String?): List<ImageResponse> {
        if (data == null) {
            return emptyList()
        }
        val type = object : TypeToken<List<ImageResponse>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun imageToString(urls: List<ImageResponse>?): String {
        if (urls.isNullOrEmpty()) {
            gson.toJson(emptyList<ImageResponse>())
        }
        return gson.toJson(urls)
    }
}