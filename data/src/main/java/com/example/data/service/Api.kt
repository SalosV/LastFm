package com.example.data.service

import com.example.data.BuildConfig.API_KEY
import com.example.data.COUNTRY
import com.example.data.FORMAT
import com.example.data.METHOD
import com.example.data.remote.models.ArtistsBase
import com.example.data.remote.models.TopArtists
import com.example.domain.models.BaseArtist
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("2.0/")
    fun getArtists(
        @Query("method") method: String = METHOD,
        @Query("country") country: String = COUNTRY,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT,
        @Query("limit") limit: Int = 10
    ): Single<TopArtists>
}