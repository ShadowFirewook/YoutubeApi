package com.example.youtubeapi.data.remote

import com.example.youtubeapi.model.Playlists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part:String,
        @Query("channelId") channelId:String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ) : Call<Playlists>
}