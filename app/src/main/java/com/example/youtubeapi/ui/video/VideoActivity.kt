package com.example.youtubeapi.ui.video

import android.os.Bundle
import android.widget.Toast
import com.example.youtubeapi.BuildConfig.API_KEY
import com.example.youtubeapi.databinding.ActivityVideoBinding
import com.example.youtubeapi.ui.playlist_detail.PlaylistDetailActivity
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer


class VideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private var videoId: String? = null
    private var videoName: String? = null
    private var videoDescription: String? = null
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        initView()
        binding.youtubePlayerView.initialize(API_KEY,this)
        initClick()
    }

    private fun getData() {
        videoId = intent.getStringExtra(PlaylistDetailActivity.VIDEO_ID)
        videoName = intent.getStringExtra(PlaylistDetailActivity.VIDEO_NAME)
        videoDescription = intent.getStringExtra(PlaylistDetailActivity.VIDEO_DESCRIPTION)
    }

    private fun initView() {
        binding.tvVideoName.text = videoName
        binding.tvVideoDescription.text = videoDescription
    }

    private fun initClick() {
        backToPlaylist()
        onClickDownload()
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.loadVideo(videoId)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Toast.makeText(this,"Возникла ошибка",Toast.LENGTH_LONG).show()
    }

    private fun backToPlaylist(){
        binding.backToPlaylist.flBackToPlaylist.setOnClickListener{
            finish()
        }
    }

    private fun onClickDownload(){

        val checkedItem = intArrayOf(-1)

        binding.downloadView.setOnClickListener{

            val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this)
            alertDialog.setTitle("Select video quality")
            val videoQualities = arrayOf("1080p", "720p", "480p")

         alertDialog.setSingleChoiceItems(videoQualities, checkedItem[0]
                ){ dialog, which ->
                    checkedItem[0] = which

                }
                alertDialog.setPositiveButton("Download"){ dialog,which ->

                }

            val customAlertDialog = alertDialog.create()
            customAlertDialog.show()
        }
    }

}