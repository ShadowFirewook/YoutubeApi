package com.example.youtubeapi.ui.playlist_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtubeapi.core.extension.loadImage
import com.example.youtubeapi.data.remote.model.PlaylistItems
import com.example.youtubeapi.databinding.ItemPlaylistItemsBinding

class PlaylistItemsAdapter(
    private val playlistItems: ArrayList<PlaylistItems.Item>,
    private val onClick:(String,String,String) -> Unit
): Adapter<PlaylistItemsAdapter.PlaylistItemsViewHolder>() {

    inner class PlaylistItemsViewHolder(private val binding: ItemPlaylistItemsBinding): ViewHolder(binding.root) {
        fun bind(item: PlaylistItems.Item){
            binding.ivVideo.loadImage(item.snippet.thumbnails.default.url)
            binding.tvVideoName.text = item.snippet.title
            binding.tvVideoDuration.text = ""

            itemView.setOnClickListener{
                onClick(item.contentDetails.videoId,item.snippet.title,item.snippet.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemsViewHolder {
        return PlaylistItemsViewHolder(ItemPlaylistItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PlaylistItemsViewHolder, position: Int) {
        holder.bind(playlistItems[position])
    }

    override fun getItemCount(): Int {
        return playlistItems.size
    }

}