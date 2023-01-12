package com.example.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.base.BaseViewModel
import com.example.youtubeapi.data.remote.ApiService
import com.example.youtubeapi.data.remote.RetrofitClient
import com.example.youtubeapi.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel: BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<Playlists>{
        return getPlaylists()
    }

    private fun getPlaylists(): LiveData<Playlists> {

        val data = MutableLiveData<Playlists>()

        apiService.getPlaylists("snippet,contentDetails","UCjfAEJZdfbIjVHdo5yODfyQ", BuildConfig.API_KEY,30)
            .enqueue(object :
                Callback<Playlists> {

                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful){
                        data.value = response.body()
                }
            }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {

            }

        })
        return data
    }

}