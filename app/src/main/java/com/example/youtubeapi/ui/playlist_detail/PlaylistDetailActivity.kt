package com.example.youtubeapi.ui.playlist_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.R
import com.example.youtubeapi.core.network.CheckInternet
import com.example.youtubeapi.core.ui.BaseActivity
import com.example.youtubeapi.data.remote.model.PlaylistItems
import com.example.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapi.ui.playlists.PlaylistsActivity
import com.example.youtubeapi.ui.video.VideoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailActivity :
    BaseActivity<PlaylistDetailViewModel, ActivityPlaylistDetailBinding>() {

    private val list = arrayListOf<PlaylistItems.Item>()
    private val playlistItemsAdapter = PlaylistItemsAdapter(list,this::onVideoClick)
    private var playlistId: String? = null
    private var videosAmount: String? = null
    private var playlistName: String? = null
    private var playlistDescription: String? = null

    override val viewModel: PlaylistDetailViewModel by viewModel()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(layoutInflater)
    }

    override fun getData() {
        playlistId = intent.getStringExtra(PlaylistsActivity.PLAYLIST_ID)
        videosAmount = intent.getStringExtra(PlaylistsActivity.VIDEOS_AMOUNT)
        playlistName = intent.getStringExtra(PlaylistsActivity.PLAYLIST_NAME)
        playlistDescription = intent.getStringExtra(PlaylistsActivity.PLAYLIST_DESCRIPTION)
    }

    override fun checkInternet() {
        val connectivity = CheckInternet(this)
        connectivity.observe(this) {
            if (it == true) {
                binding.noInternetConnection.root.visibility = View.GONE
                binding.container.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.root.visibility = View.VISIBLE
                binding.container.visibility = View.GONE
            }
        }
    }

    override fun initAdapter() {
        binding.rvPlaylistItems.apply {
            layoutManager = LinearLayoutManager(this@PlaylistDetailActivity)
            adapter = playlistItemsAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initViewModel() {
        playlistId?.let {
            viewModel.getPlaylistItems(it).observe(this){ model ->
                list.addAll(model.items)
                binding.tvVideosAmount.text = getString(R.string.video_series, videosAmount)
                binding.tvPlaylistName.text = playlistName
                binding.tvPlaylistDescription.text = playlistDescription
                playlistItemsAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun onVideoClick(videoId:String, videoName: String, videoDescription: String){
        val intent = Intent(this,VideoActivity::class.java)
        intent.putExtra(VIDEO_ID, videoId)
        intent.putExtra(VIDEO_NAME, videoName)
        intent.putExtra(VIDEO_DESCRIPTION, videoDescription)
        startActivity(intent)
    }

    override fun initClick() {
        backToPlaylist()
        playPlaylistVideos()
    }

    private fun backToPlaylist(){
        binding.backToPlaylist.flBackToPlaylist.setOnClickListener{
            finish()
        }
    }

    private fun playPlaylistVideos(){
        binding.fabPlay.setOnClickListener{
            val intent = Intent(this,VideoActivity::class.java)
            intent.putExtra(VIDEO_ID,list[0].contentDetails.videoId)
            intent.putExtra(VIDEO_NAME, list[0].snippet.title)
            intent.putExtra(VIDEO_DESCRIPTION, list[0].snippet.description)
            startActivity(intent)
        }
    }

    companion object{
        const val VIDEO_ID = "video id"
        const val VIDEO_NAME = "video name"
        const val VIDEO_DESCRIPTION = "video description"
    }

}