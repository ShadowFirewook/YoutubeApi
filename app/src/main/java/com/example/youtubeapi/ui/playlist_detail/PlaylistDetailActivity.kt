package com.example.youtubeapi.ui.playlist_detail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapi.ui.playlists.PlaylistsActivity
import com.example.youtubeapi.ui.playlists.PlaylistsViewModel

class PlaylistDetailActivity : BaseActivity<PlaylistsViewModel, ActivityPlaylistDetailBinding>() {

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val result = intent.getStringExtra(PlaylistsActivity.PLAYLIST_ID)
        Toast.makeText(this,result,Toast.LENGTH_LONG).show()
    }

}