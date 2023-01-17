package com.example.youtubeapi.data.remote

import com.example.youtubeapi.data.remote.model.Playlists
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part:String,
        @Query("channelId") channelId:String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ) : Response<Playlists>
}