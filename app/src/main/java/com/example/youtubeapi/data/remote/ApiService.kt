package com.example.youtubeapi.data.remote

import com.example.youtubeapi.data.remote.model.PlaylistItems
import com.example.youtubeapi.data.remote.model.Playlists
import com.example.youtubeapi.data.remote.model.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ): Response<Playlists>

    @GET("playlistItems")
    suspend fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ): Response<PlaylistItems>

    @GET("videos")
    suspend fun getVideo(
        @Query("part") part: String,
        @Query("id") id: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ): Response<Video>
}