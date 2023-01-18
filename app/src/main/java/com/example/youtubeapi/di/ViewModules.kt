package com.example.youtubeapi.di

import com.example.youtubeapi.ui.playlist_detail.PlaylistDetailViewModel
import com.example.youtubeapi.ui.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { PlaylistDetailViewModel(get()) }
}