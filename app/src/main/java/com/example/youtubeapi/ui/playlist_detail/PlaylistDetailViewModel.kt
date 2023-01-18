package com.example.youtubeapi.ui.playlist_detail

import androidx.lifecycle.LiveData
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.data.remote.model.PlaylistItems
import com.example.youtubeapi.repository.Repository

class PlaylistDetailViewModel(private val repository: Repository): BaseViewModel() {

    fun getPlaylistItems(playlistId: String): LiveData<PlaylistItems> {
        return repository.getPlaylistItems(playlistId)
    }

}