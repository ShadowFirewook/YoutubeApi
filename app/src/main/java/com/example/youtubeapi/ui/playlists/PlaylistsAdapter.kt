package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtubeapi.databinding.ItemPlaylistBinding
import com.example.youtubeapi.loadImage
import com.example.youtubeapi.model.Playlists

class PlaylistsAdapter(
    private val playlistsList:ArrayList<Playlists.Item>,
    private val onClick:(String) -> Unit
): Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        val playlist = playlistsList[position]
        holder.bind(playlist)
    }

    override fun getItemCount(): Int {
        return playlistsList.size
    }

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistBinding): ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(item: Playlists.Item){
            binding.ivPlaylist.loadImage(item.snippet.thumbnails.default.url)
            binding.tvVideoName.text = item.snippet.title
            binding.tvVideosAmount.text = "${item.contentDetails.itemCount} video series"

            itemView.setOnClickListener{
                onClick(item.id)
            }
        }

    }

}