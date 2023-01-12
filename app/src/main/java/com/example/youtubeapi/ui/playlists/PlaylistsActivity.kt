package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistsBinding
import com.example.youtubeapi.model.Playlists
import com.example.youtubeapi.ui.playlist_detail.PlaylistDetailActivity

class PlaylistsActivity : BaseActivity<PlaylistsViewModel,ActivityPlaylistsBinding>() {

    private val list = arrayListOf<Playlists.Item>()
    private val playlistsAdapter = PlaylistsAdapter(list,this::onPlaylistClick)

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun checkInternet() {
        isOnline(this)
    }

    override fun initAdapter() {
        binding.rvPlaylists.apply {
            layoutManager = LinearLayoutManager(this@PlaylistsActivity)
            adapter = playlistsAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initViewModel() {
        viewModel.playlists().observe(this){
            list.addAll(it.items)
            playlistsAdapter.notifyDataSetChanged()
        }
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    private fun onPlaylistClick(id:String){
        val intent = Intent(this,PlaylistDetailActivity::class.java)
        intent.putExtra(PLAYLIST_ID,id)
        startActivity(intent)
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        binding.noInternetConnection.root.visibility = View.VISIBLE
        return false
    }

    companion object{
        const val PLAYLIST_ID = "playlist id"
    }

}