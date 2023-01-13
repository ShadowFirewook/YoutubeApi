package com.example.youtubeapi.core

import android.app.Application
import com.example.youtubeapi.repository.Repository

class App: Application() {

    companion object{
        val repository: Repository by lazy {
            Repository()
        }
    }

}