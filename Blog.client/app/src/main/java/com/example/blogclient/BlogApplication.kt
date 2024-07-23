package com.example.blogclient

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BlogApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}