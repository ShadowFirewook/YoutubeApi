package com.example.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.remote.ApiService
import com.example.youtubeapi.data.remote.model.PlaylistItems
import com.example.youtubeapi.data.remote.model.Playlists
import kotlinx.coroutines.Dispatchers

class Repository(private var apiService: ApiService) {

    fun getPlaylists(): LiveData<Playlists> {
        return liveData(Dispatchers.IO){
            val response = apiService.getPlaylists("snippet,contentDetails","UCjfAEJZdfbIjVHdo5yODfyQ", BuildConfig.API_KEY,50)
            response.body()?.let { emit(it) }
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<PlaylistItems> {
        return liveData(Dispatchers.IO){
            val response = apiService.getPlaylistItems("snippet,contentDetails",playlistId, BuildConfig.API_KEY,50)
            response.body()?.let { emit(it) }
        }
    }

}