package com.example.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import com.example.youtubeapi.core.App
import com.example.youtubeapi.core.ui.BaseViewModel
import com.example.youtubeapi.data.remote.model.Playlists

class PlaylistsViewModel: BaseViewModel() {

    fun getPlaylists(): LiveData<Playlists>{
        return App.repository.getPlaylists()
    }

}