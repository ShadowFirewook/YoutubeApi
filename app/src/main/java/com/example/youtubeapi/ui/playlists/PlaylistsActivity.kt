package com.example.youtubeapi.ui.playlists

import com.example.youtubeapi.core.network.CheckInternet
import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistsBinding
import com.example.youtubeapi.data.remote.model.Playlists
import com.example.youtubeapi.ui.playlist_detail.PlaylistDetailActivity

class PlaylistsActivity : BaseActivity<PlaylistsViewModel, ActivityPlaylistsBinding>() {

    private val list = arrayListOf<Playlists.Item>()
    private val playlistsAdapter = PlaylistsAdapter(list,this::onPlaylistClick)

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun checkInternet() {
        val connectivity = CheckInternet(this)
        connectivity.observe(this) {
            if (it == true) {
                binding.noInternetConnection.root.visibility = View.GONE
                binding.rvPlaylists.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.root.visibility = View.VISIBLE
                binding.rvPlaylists.visibility = View.GONE
            }
        }
    }

    override fun initAdapter() {
        binding.rvPlaylists.apply {
            layoutManager = LinearLayoutManager(this@PlaylistsActivity)
            adapter = playlistsAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initViewModel() {
        viewModel.getPlaylists().observe(this){
            list.addAll(it.items)
            playlistsAdapter.notifyDataSetChanged()
        }
    }

    private fun onPlaylistClick(id:String){
        val intent = Intent(this,PlaylistDetailActivity::class.java)
        intent.putExtra(PLAYLIST_ID,id)
        startActivity(intent)
    }

    companion object{
        const val PLAYLIST_ID = "playlist id"
    }

}