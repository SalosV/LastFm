package com.example.data.service

import com.example.data.*
import com.example.data.BuildConfig.API_KEY
import com.example.data.COUNTRY
import com.example.data.FORMAT
import com.example.data.METHOD_ALL_ARTISTS
import com.example.data.METHOD_SEARCH_ARTIST
import com.example.data.remote.models.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("2.0/")
    fun getArtists(
        @Query("method") method: String = METHOD_ALL_ARTISTS,
        @Query("country") country: String = COUNTRY,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT,
        @Query("limit") limit: Int = 10
    ): Single<TopArtists>

    @GET("2.0/")
    fun getDetailArtist(
        @Query("method") method: String = METHOD_DETAIL_ARTIST,
        @Query("artist") artist: String,
        @Query("mbid") mbid: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT
    ): Single<DetailArtist>

    @GET("2.0/")
    fun getSearchArtists(
        @Query("artist") artist: String,
        @Query("method") method: String = METHOD_SEARCH_ARTIST,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT
    ): Single<Result<ArtistMatches>>

    @GET("2.0/")
    fun getTracks(
        @Query("method") method: String = METHOD_TOP_TRACKS,
        @Query("country") country: String = COUNTRY,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT,
        @Query("limit") limit: Int = 10
    ): Single<TracksResponse>

    @GET("2.0/")
    fun getSearchTracks(
        @Query("track") track: String,
        @Query("method") method: String = METHOD_SEARCH_TRACK,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = FORMAT
    ): Single<Result<TrackMatches>>
}